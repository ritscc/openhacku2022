package cc.rits.openhacku2022.api.controller

import cc.rits.openhacku2022.api.response.TransactionResponse
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.ForbiddenException
import cc.rits.openhacku2022.exception.NotFoundException
import cc.rits.openhacku2022.exception.UnauthorizedException
import cc.rits.openhacku2022.helper.DateHelper
import cc.rits.openhacku2022.helper.TableHelper
import org.springframework.http.HttpStatus

/**
 * 取引コントローラーの統合テスト
 */
class TransactionRestController_IT extends BaseRestController_IT {

    // API PATH
    static final String BASE_PATH = "/api/shop/%d/transactions/me"
    static final String GET_TRANSACTION_PATH = BASE_PATH

    def "取引取得API: 正常系 顧客が取引を取得できる"() {
        given:
        this.login()

        // @formatter:off
        TableHelper.insert sql, "order", {
            id | transaction_id | ordered_date
            1  | 1              | DateHelper.today()
            2  | 1              | DateHelper.today()
        }
        TableHelper.insert sql, "menu", {
            id | shop_id | name | price | image_url
            1  | 1       | ""   | 100   | ""
            2  | 1       | ""   | 200   | ""
        }
        TableHelper.insert sql, "order_menu", {
            order_id | menu_id | quantity | status
            1        | 1       | 3        | 0
            2        | 1       | 4        | 0
            2        | 2       | 5        | 0
        }
        // @formatter:on

        when:
        final request = this.getRequest(String.format(GET_TRANSACTION_PATH, 1))
        final response = this.execute(request, HttpStatus.OK, TransactionResponse)

        then:
        response.id == 1
        response.table.id == 1
        response.table.capacity == 4
        response.orders*.id == [1, 2]
        response.orders*.transactionId == [1, 1]
        response.orders*.orderMenus.menu*.id == [[1], [1, 2]]
        response.orders*.orderMenus.menu*.price == [[100], [100, 200]]
        response.orders*.orderMenus*.quantity == [[3], [4, 5]]
    }

    def "取引取得API: 異常系 取引中の店舗出ない場合は403エラー"() {
        given:
        this.login()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            2  | ""   | ""   | ""
        }
        // @formatter:on

        expect:
        final request = this.getRequest(String.format(GET_TRANSACTION_PATH, 2))
        this.execute(request, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "取引取得API: 異常系 店舗が存在しない場合は404エラー"() {
        given:
        this.login()

        expect:
        final request = this.getRequest(String.format(GET_TRANSACTION_PATH, 2))
        this.execute(request, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "取引取得API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.getRequest(String.format(GET_TRANSACTION_PATH, 2))
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }
}

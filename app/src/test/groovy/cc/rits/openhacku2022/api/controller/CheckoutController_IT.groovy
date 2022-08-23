package cc.rits.openhacku2022.api.controller

import cc.rits.openhacku2022.enums.OrderStatusEnum
import cc.rits.openhacku2022.exception.BadRequestException
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.UnauthorizedException
import cc.rits.openhacku2022.helper.DateHelper
import cc.rits.openhacku2022.helper.TableHelper
import org.springframework.http.HttpStatus

/**
 * CheckoutControllerの統合テスト
 */
class CheckoutController_IT extends BaseRestController_IT {
    // API PATH
    static final String BASE_PATH = "/api/checkout"
    static final String POST_CHECKOUT_PATH = BASE_PATH

    def "支払いAPI: 正常系 顧客が支払いをできる"() {
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
            1        | 1       | 3        | OrderStatusEnum.COMPLETED.id
            2        | 1       | 4        | OrderStatusEnum.COMPLETED.id
            2        | 2       | 5        | OrderStatusEnum.COMPLETED.id
        }
        // @formatter:on

        when:
        final request = this.postRequest(POST_CHECKOUT_PATH)
        final response = this.execute(request, HttpStatus.SEE_OTHER)

        then:
        response.getResponse().getContentAsString() == ""
    }

    def "支払いAPI: 異常系 注文をしていない場合は400エラー"() {
        given:
        this.login()

        expect:
        final request = this.postRequest(POST_CHECKOUT_PATH)
        this.execute(request, new BadRequestException(ErrorCode.ORDERS_ARE_NOT_COMPLETED))
    }

    def "支払いAPI: 異常系 全ての注文が提供済みでない場合は400エラー"() {
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
            1        | 1       | 3        | OrderStatusEnum.COMPLETED.id
            2        | 1       | 4        | OrderStatusEnum.COMPLETED.id
            2        | 2       | 5        | OrderStatusEnum.IN_PREPARATION.id
        }
        // @formatter:on

        expect:
        final request = this.postRequest(POST_CHECKOUT_PATH)
        this.execute(request, new BadRequestException(ErrorCode.ORDERS_ARE_NOT_COMPLETED))
    }

    def "支払いAPI: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.postRequest(POST_CHECKOUT_PATH)
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

}

package cc.rits.openhacku2022.api.controller

import cc.rits.openhacku2022.api.response.MenusResponse
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.ForbiddenException
import cc.rits.openhacku2022.exception.NotFoundException
import cc.rits.openhacku2022.exception.UnauthorizedException
import cc.rits.openhacku2022.helper.TableHelper
import org.springframework.http.HttpStatus

/**
 * メニューコントローラーの統合テスト
 */
class MenuRestController_IT extends BaseRestController_IT {

    // API PATH
    static final String BASE_PATH = "/api/shops/%d/menus"
    static final String GET_MENUS_PATH = BASE_PATH

    def "メニューリスト取得API: 正常系 顧客がメニューリストを取得できる"() {
        given:
        this.login()

        // @formatter:off
        TableHelper.insert sql, "menu", {
            id | shop_id | name | price | image_url
            1  | 1       | ""   | 100   | ""
            2  | 1       | ""   | 100   | ""
        }
        // @formatter:on

        when:
        final request = this.getRequest(String.format(GET_MENUS_PATH, 1))
        final response = execute(request, HttpStatus.OK, MenusResponse)

        then:
        response.menus*.id == [1, 2]
        response.menus*.shopId == [1, 1]
        response.menus*.price == [100, 100]
    }

    def "メニューリスト取得API: 異常系 店舗が存在しない場合は404エラー"() {
        given:
        this.login()

        expect:
        final request = this.getRequest(String.format(GET_MENUS_PATH, 0))
        this.execute(request, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "メニューリスト取得API: 異常系 取引外の店舗の場合は403エラー"() {
        given:
        this.login()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            2  | ""   | "2"  | ""
        }
        // @formatter:on

        expect:
        final request = this.getRequest(String.format(GET_MENUS_PATH, 2))
        execute(request, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "メニューリスト取得API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.getRequest(String.format(GET_MENUS_PATH, 1))
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

}

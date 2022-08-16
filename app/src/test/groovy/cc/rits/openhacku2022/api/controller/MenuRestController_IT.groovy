package cc.rits.openhacku2022.api.controller

import cc.rits.openhacku2022.api.response.MenusResponse
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.UnauthorizedException
import cc.rits.openhacku2022.helper.TableHelper
import org.springframework.http.HttpStatus

/**
 * メニューコントローラーの統合テスト
 */
class MenuRestController_IT extends BaseRestController_IT {

    // API PATH
    static final String BASE_PATH = "/api/shop/%d"
    static final String GET_MENUS_PATH = BASE_PATH + "/menus"

    def "メニューリスト取得API: 正常系　顧客がメニューリストを取得できる"() {
        given:
        this.createLoginUser()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | code | name | description | password
            1  | "A"  | ""   | ""          | ""
        }
        TableHelper.insert sql, "menu", {
            id | shop_id | name | description | price | image_url
            1  | 1       | ""   | ""          | 100   | ""
            2  | 1       | ""   | ""          | 100   | ""
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

    def "メニューリスト取得API: 異常系 ログインしていない場合は401エラー"() {
        final request = this.getRequest(String.format(GET_MENUS_PATH, 1))
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

}

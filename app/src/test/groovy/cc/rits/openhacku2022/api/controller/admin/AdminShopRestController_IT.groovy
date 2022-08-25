package cc.rits.openhacku2022.api.controller.admin

import cc.rits.openhacku2022.api.controller.BaseRestController_IT
import cc.rits.openhacku2022.api.response.ShopResponse
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.UnauthorizedException
import cc.rits.openhacku2022.helper.TableHelper
import org.springframework.http.HttpStatus

/**
 * AdminShopRestControllerの統合テスト
 */
class AdminShopRestController_IT extends BaseRestController_IT {
    // API PATH
    static final BASE_PATH = "/api/admin/shops"
    static final GET_SHOP_PATH = BASE_PATH + "/me"

    def "店舗取得API: 正常系 管理者が自身の店舗を取得できる"() {
        given:
        final shop = this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop_table", {
            id | shop_id | number | capacity
            1  | 1       | 1      | 4
            2  | 1       | 2      | 5
        }
        // @formatter:on

        when:
        final request = this.getRequest(GET_SHOP_PATH)
        final response = this.execute(request, HttpStatus.OK, ShopResponse)

        then:
        response.id == shop.id
        response.name == shop.name
        response.code == shop.code
        response.tables*.id == [1, 2]
        response.tables*.tableNumber == [1, 2]
        response.tables*.capacity == [4, 5]
        response.tables*.isUsed == [false, false]
    }

    def "店舗取得API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.getRequest(GET_SHOP_PATH)
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

}

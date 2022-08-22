package cc.rits.openhacku2022.api.controller.admin

import cc.rits.openhacku2022.api.controller.BaseRestController_IT
import cc.rits.openhacku2022.api.response.ShopResponse
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.UnauthorizedException
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
        this.loginShop()

        when:
        final request = this.getRequest(GET_SHOP_PATH)
        final response = this.execute(request, HttpStatus.OK, ShopResponse)

        then:
        response.id == 1
        Objects.nonNull(response.name)
        Objects.nonNull(response.code)
    }

    def "店舗取得API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.getRequest(GET_SHOP_PATH)
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

}

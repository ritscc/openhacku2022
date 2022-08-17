package cc.rits.openhacku2022.api.controller.admin

import cc.rits.openhacku2022.api.controller.BaseRestController_IT
import cc.rits.openhacku2022.api.request.AdminLoginRequest
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.UnauthorizedException
import org.springframework.http.HttpStatus

/**
 * AdminAuthRestControllerの統合テスト
 */
class AdminAuthRestController_IT extends BaseRestController_IT {

    // API PATH
    static final String BASE_PATH = "/api/admin"
    static final String LOGIN_PATH = BASE_PATH + "/login"

    def "ログインAPI: 正常系 店舗にログイン"() {
        given:
        final shop = this.loginShop()
        final oldSessionId = this.session.getId()

        final requestBody = AdminLoginRequest.builder()
            .code(shop.code)
            .password(shop.password)
            .build()

        when:
        final request = this.postRequest(LOGIN_PATH, requestBody)
        this.execute(request, HttpStatus.OK)

        then:
        // セッションの有効期限が3時間
        session.getMaxInactiveInterval() == 10800
        // セッションIDが変更されていることを確認 (セッションジャック対策)
        oldSessionId != this.session.getId()
    }

    def "ログインAPI: 異常系 店舗コードかパスワードが間違えている場合は401エラー"() {
        given:
        final shop = this.loginShop()
        final requestBody = AdminLoginRequest.builder()
            .code(isCodeIncorrect ? shop.code + "XXX" : shop.code)
            .password(isPasswordIncorrect ? shop.password + "XXX" : shop.password)
            .build()

        expect:
        final request = this.postRequest(LOGIN_PATH, requestBody)
        this.execute(request, new UnauthorizedException(expectedErrorCode))

        where:
        isCodeIncorrect | isPasswordIncorrect || expectedErrorCode
        true            | false               || ErrorCode.INCORRECT_CODE_OR_PASSWORD
        false           | true                || ErrorCode.INCORRECT_CODE_OR_PASSWORD
    }

}

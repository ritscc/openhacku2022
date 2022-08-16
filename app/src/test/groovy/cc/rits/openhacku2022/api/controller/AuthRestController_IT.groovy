package cc.rits.openhacku2022.api.controller

import cc.rits.openhacku2022.api.request.UserLoginRequest
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.UnauthorizedException
import org.springframework.http.HttpStatus

/**
 * AuthRestControllerの統合テスト
 */
class AuthRestController_IT extends BaseRestController_IT {

    // API PATH
    static final String BASE_PATH = "/api"
    static final String LOGIN_PATH = BASE_PATH + "/login"

    def "ログインAPI: 正常系 ログインに成功するとセッションに記録される"() {
        given:
        final loginUser = this.createLoginUser()
        final oldSessionId = this.session.getId()

        when:
        final loginRequest = UserLoginRequest.builder()
            .email(loginUser.getEmail())
            .password(loginUser.getPassword())
            .build()
        final request = this.postRequest(LOGIN_PATH, loginRequest)
        this.execute(request, HttpStatus.OK)

        then:
        // セッションIDが変更されている
        oldSessionId != this.session.getId()
    }

    def "ログインAPI: 異常系 メールアドレスかパスワードが間違えていれば401エラー"() {
        given:
        this.createLoginUser()

        expect:
        final loginRequest = UserLoginRequest.builder()
            .email(inputEmail)
            .password(inputPassword)
            .build()
        final request = this.postRequest(LOGIN_PATH, loginRequest)
        this.execute(request, new UnauthorizedException(ErrorCode.INCORRECT_EMAIL_OR_PASSWORD))

        where:
        inputEmail               | inputPassword
        LOGIN_USER_EMAIL + "xxx" | LOGIN_USER_PASSWORD
        LOGIN_USER_EMAIL         | LOGIN_USER_PASSWORD + "xxx"
    }

}

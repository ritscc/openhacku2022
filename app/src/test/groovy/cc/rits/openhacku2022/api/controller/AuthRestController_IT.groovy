package cc.rits.openhacku2022.api.controller

import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.UnauthorizedException
import org.springframework.http.HttpStatus

/**
 * AuthRestControllerの統合テスト
 */
class AuthRestController_IT extends BaseRestController_IT {

    // API PATH
    static final String BASE_PATH = "/api"
    static final String LOGOUT_PATH = BASE_PATH + "/logout"

    def "ログアウトAPI: 正常系 ログアウトするとセッションが無効になり、取引が削除される"() {
        given:
        this.login()

        when:
        final request = this.postRequest(LOGOUT_PATH)
        this.execute(request, HttpStatus.OK)

        then:
        this.session.isInvalid()
        sql.rows("select * from transaction") == []
    }

    def "ログアウトAPI: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.postRequest(LOGOUT_PATH)
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

}

package cc.rits.openhacku2022.service.admin

import cc.rits.openhacku2022.BaseSpecification
import cc.rits.openhacku2022.api.request.AdminLoginRequest
import cc.rits.openhacku2022.auth.AdminAuthenticationProvider
import cc.rits.openhacku2022.helper.RandomHelper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

/**
 * AdminAuthService_UTの単体テスト
 */
class AdminAuthService_UT extends BaseSpecification {

    @Autowired
    AdminAuthService sut

    @SpringBean
    AdminAuthenticationProvider authenticationProvider = Mock()

    @SpringBean
    HttpSession httpSession = Mock()

    @SpringBean
    HttpServletRequest httpServletRequest = Mock()

    def "login: ログインに成功すると、セッションに記録される"() {
        given:
        final requestBody = RandomHelper.mock(AdminLoginRequest)

        when:
        this.sut.login(requestBody)

        then:
        1 * this.authenticationProvider.authenticate(_)
        1 * this.httpSession.setMaxInactiveInterval(10800)
        1 * this.httpServletRequest.changeSessionId()
    }

    def "logout: セッションが廃棄される"() {
        when:
        this.sut.logout()

        then:
        1 * this.httpSession.invalidate()
    }

}

package cc.rits.openhacku2022.api.controller

import org.springframework.http.HttpStatus

/**
 * HealthRestControllerの統合テスト
 */
class HealthCheckRestController_IT extends BaseRestController_IT {

    // API PATH
    static final String BASE_PATH = "/api/health"
    static final String HEALTH_CHECK_PATH = BASE_PATH

    def "正常系 APIが正常に動いている"() {
        expect:
        final request = this.getRequest(HEALTH_CHECK_PATH)
        this.execute(request, HttpStatus.OK)
    }

}

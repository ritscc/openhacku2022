package cc.rits.openhacku2022.api.controller

import cc.rits.openhacku2022.api.response.ErrorResponse
import org.springframework.http.HttpStatus

/**
 * GlobalRestControllerAdviceの統合テスト
 */
class GlobalRestControllerAdvice_IT extends BaseRestController_IT {

    def "異常系 存在しないパスにリクエストすると404を返す"() {
        given:
        final path = "/api/not_exists_path"

        when:
        final request = this.getRequest(path)
        final response = this.execute(request, HttpStatus.NOT_FOUND, ErrorResponse.class)

        then:
        response.code == HttpStatus.NOT_FOUND.value()
        response.message == "APIが見つかりません。"
        !response.message.isBlank()
    }

}

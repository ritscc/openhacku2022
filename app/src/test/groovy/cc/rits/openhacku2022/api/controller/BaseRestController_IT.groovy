package cc.rits.openhacku2022.api.controller

import cc.rits.openhacku2022.BaseSpecification
import cc.rits.openhacku2022.api.response.ErrorResponse
import cc.rits.openhacku2022.auth.AdminUserDetails
import cc.rits.openhacku2022.exception.BaseException
import cc.rits.openhacku2022.helper.JsonConvertHelper
import cc.rits.openhacku2022.helper.RandomHelper
import cc.rits.openhacku2022.helper.TableHelper
import cc.rits.openhacku2022.model.ShopModel
import cc.rits.openhacku2022.model.TransactionModel
import cc.rits.openhacku2022.util.AuthUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpSession
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.util.MultiValueMap
import org.springframework.web.context.WebApplicationContext
import spock.lang.Shared

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME

/**
 * RestController統合テストの基底クラス
 */
abstract class BaseRestController_IT extends BaseSpecification {

    private MockMvc mockMvc

    @Autowired
    private WebApplicationContext webApplicationContext

    @Autowired
    private PlatformTransactionManager transactionManager

    @Autowired
    private MessageSource messageSource

    @Autowired
    protected AuthUtil authUtil

    @Shared
    protected MockHttpSession session = new MockHttpSession()

    @Shared
    protected Authentication authentication

    /**
     * GET request
     *
     * @param path path
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder getRequest(final String path) {
        return MockMvcRequestBuilders.get(path).with(authentication(this.authentication)).session(this.session)
    }

    /**
     * POST request
     *
     * @param path path
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder postRequest(final String path) {
        return MockMvcRequestBuilders.post(path).with(authentication(this.authentication)).session(this.session)
    }

    /**
     * POST request (Form)
     *
     * @param path path
     * @param params query params
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder postRequest(final String path, final MultiValueMap<String, String> params) {
        return MockMvcRequestBuilders.post(path)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .params(params)
            .with(authentication(this.authentication))
            .session(this.session)
    }

    /**
     * POST request (JSON)
     *
     * @param path path
     * @param content request body
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder postRequest(final String path, final Object content) {
        return MockMvcRequestBuilders.post(path)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(JsonConvertHelper.convertObjectToJson(content))
            .with(authentication(this.authentication))
            .session(this.session)
    }

    /**
     * PUT request (JSON)
     *
     * @param path path
     * @param content request body
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder putRequest(final String path, final Object content) {
        return MockMvcRequestBuilders.put(path)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(JsonConvertHelper.convertObjectToJson(content))
            .with(authentication(this.authentication))
            .session(this.session)
    }

    /**
     * DELETE request
     *
     * @param path path
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder deleteRequest(final String path) {
        return MockMvcRequestBuilders.delete(path).with(authentication(this.authentication)).session(this.session)
    }

    /**
     * Execute request
     *
     * @param request HTTP request builder
     * @param status expected HTTP status
     *
     * @return MVC result
     */
    MvcResult execute(final MockHttpServletRequestBuilder request, final HttpStatus status) {
        final result = mockMvc.perform(request).andReturn()

        assert result.response.status == status.value()
        return result
    }

    /**
     * Execute request / return response
     *
     * @param request HTTP request builder
     * @param status expected HTTP status
     * @param clazz response class
     *
     * @return response
     */
    def <T> T execute(final MockHttpServletRequestBuilder request, final HttpStatus status, final Class<T> clazz) {
        final result = mockMvc.perform(request).andReturn()

        assert result.response.status == status.value()
        return JsonConvertHelper.convertJsonToObject(result.getResponse().getContentAsString(), clazz)
    }

    /**
     * Execute request / verify exception
     *
     * @param request HTTP request builder
     * @param exception expected exception
     *
     * @return error response
     */
    ErrorResponse execute(final MockHttpServletRequestBuilder request, final BaseException exception) {
        final result = mockMvc.perform(request).andReturn()
        final response = JsonConvertHelper.convertJsonToObject(result.response.contentAsString, ErrorResponse.class)

        final expectedErrorMessage = this.getErrorMessage(exception)

        assert result.response.status == exception.httpStatus.value()
        assert response.code == exception.errorCode.status.value()
        assert response.message == expectedErrorMessage
        return response
    }

    /**
     * エラーメッセージを取得
     *
     * @param exception exception
     * @return エラーメッセージ
     */
    private String getErrorMessage(final BaseException exception) {
        final messageKey = exception.errorCode.messageKey
        final args = exception.args
        return this.messageSource.getMessage(messageKey, args, Locale.ENGLISH)
    }

    /**
     * ログイン
     */
    protected TransactionModel login() {
        final transaction = TransactionModel.builder()
            .id(1)
            .code(RandomHelper.uuid())
            .numberOfPeople(4)
            .build()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name                          | code                                   | password
            1  | RandomHelper.alphanumeric(10) | RandomHelper.alphanumeric(10) | RandomHelper.alphanumeric(10)
        }
        TableHelper.insert sql, "shop_table", {
            id | shop_id | number | capacity
            1  | 1       | 1      | 4
        }
        TableHelper.insert sql, "transaction", {
            id             | shop_id | table_id | code             | number_of_people
            transaction.id | 1       | 1        | transaction.code | 4
        }
        // @formatter:on

        this.session = new MockHttpSession()
        this.session.setAttribute(PRINCIPAL_NAME_INDEX_NAME, transaction.code)

        return transaction
    }

    /**
     * 店舗ログイン
     */
    protected ShopModel loginShop() {
        final shop = ShopModel.builder()
            .id(1)
            .name(RandomHelper.alphanumeric(10))
            .code(RandomHelper.alphanumeric(10))
            .password(RandomHelper.password())
            .build()

        sql.dataSet("shop").add(
            id: shop.id,
            name: shop.name,
            code: shop.code,
            password: this.authUtil.hashingPassword(shop.password)
        )

        final authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN")
        final adminUserDetails = new AdminUserDetails(shop, authorities)
        this.authentication = new UsernamePasswordAuthenticationToken(adminUserDetails, shop.password, authorities)

        return shop
    }

    /**
     * ログアウト
     */
    protected void logout() {
        if (!this.session.invalid) {
            this.session.invalidate()
        }
        this.authentication = null
    }

    /**
     * setup before test case
     */
    def setup() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.webApplicationContext)
            .addFilter(({ request, response, chain ->
                response.setCharacterEncoding("UTF-8")
                chain.doFilter(request, response)
            }))
            .apply(springSecurity())
            .build()
    }

    /**
     * cleanup after test case
     */
    def cleanup() {
        this.logout()
    }

}

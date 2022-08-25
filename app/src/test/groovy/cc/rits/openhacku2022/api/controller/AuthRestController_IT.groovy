package cc.rits.openhacku2022.api.controller

import cc.rits.openhacku2022.api.request.LoginRequest
import cc.rits.openhacku2022.exception.BadRequestException
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.NotFoundException
import cc.rits.openhacku2022.exception.UnauthorizedException
import cc.rits.openhacku2022.helper.TableHelper
import org.springframework.http.HttpStatus

import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME

/**
 * AuthRestControllerの統合テスト
 */
class AuthRestController_IT extends BaseRestController_IT {

    // API PATH
    static final String BASE_PATH = "/api"
    static final String LOGIN_PATH = BASE_PATH + "/login"
    static final String LOGOUT_PATH = BASE_PATH + "/logout"

    def "ログインAPI: 正常系 ログインできる"() {
        given:
        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            1  | ""   | ""   | ""
        }
        TableHelper.insert sql, "shop_table", {
            id | shop_id | number | capacity
            1  | 1       | 1      | 3
            2  | 1       | 2      | 2
            3  | 1       | 3      | 1
        }
        // @formatter:on

        final requestBody = LoginRequest.builder()
            .shopId(1)
            .numberOfPeople(2)
            .build()

        when:
        final request = this.postRequest(LOGIN_PATH, requestBody)
        this.execute(request, HttpStatus.OK)

        then:
        final createdTransaction = sql.firstRow("SELECT * FROM transaction")
        createdTransaction.shop_id == 1
        createdTransaction.table_id == 2
        createdTransaction.number_of_people == requestBody.numberOfPeople
        !this.session.isInvalid()
        this.session.getAttribute(PRINCIPAL_NAME_INDEX_NAME).toString() == createdTransaction.code
    }

    def "ログインAPI: 異常系 リクエストボディのバリデーション"() {
        given:
        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            1  | ""   | ""   | ""
        }
        TableHelper.insert sql, "shop_table", {
            id | shop_id | number | capacity
            1  | 1       | 1      | 4
        }
        // @formatter:on

        final requestBody = LoginRequest.builder()
            .shopId(inputShopId)
            .numberOfPeople(inputNumberOfPeople)
            .build()

        expect:
        final request = this.postRequest(LOGIN_PATH, requestBody)
        this.execute(request, expectedException)

        where:
        inputShopId | inputNumberOfPeople || expectedException
        0           | 1                   || new NotFoundException(ErrorCode.NOT_FOUND_SHOP)
        1           | 0                   || new BadRequestException(ErrorCode.INVALID_NUMBER_OF_PEOPLE)
    }

    def "ログインAPI: 異常系 利用可能なテーブルが存在しない場合は400エラー"() {
        given:
        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            1  | ""   | ""   | ""
        }
        TableHelper.insert sql, "shop_table", {
            id | shop_id | number |capacity
            1  | 1       | 1      | 3
            2  | 1       | 2      | 4
        }
        TableHelper.insert sql, "transaction", {
            id | shop_id | table_id | code | number_of_people
            1  | 1       | 2        | ""   | 4
        }
        // @formatter:on

        final requestBody = LoginRequest.builder()
            .shopId(1)
            .numberOfPeople(4)
            .build()

        expect:
        final request = this.postRequest(LOGIN_PATH, requestBody)
        this.execute(request, new BadRequestException(ErrorCode.ALL_TABLES_ARE_BOOKED))
    }

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

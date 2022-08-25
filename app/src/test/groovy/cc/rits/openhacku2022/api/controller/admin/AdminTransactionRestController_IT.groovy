package cc.rits.openhacku2022.api.controller.admin

import cc.rits.openhacku2022.api.controller.BaseRestController_IT
import cc.rits.openhacku2022.api.response.TransactionResponse
import cc.rits.openhacku2022.api.response.TransactionsResponse
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.ForbiddenException
import cc.rits.openhacku2022.exception.NotFoundException
import cc.rits.openhacku2022.exception.UnauthorizedException
import cc.rits.openhacku2022.helper.DateHelper
import cc.rits.openhacku2022.helper.RandomHelper
import cc.rits.openhacku2022.helper.TableHelper
import org.springframework.http.HttpStatus
import spock.lang.Unroll

/**
 * AdminTransactionRestControllerの統合テスト
 */
class AdminTransactionRestController_IT extends BaseRestController_IT {
    // API PATH
    static final String BASE_PATH = "/api/admin/shops/%d/transactions"
    static final String GET_TRANSACTIONS_PATH = BASE_PATH
    static final String GET_TRANSACTION_PATH = BASE_PATH + "/%d"
    static final String DELETE_TRANSACTION_PATH = BASE_PATH + "/%d"
    static final String DELETE_ALL_TRANSACTION_PATH = BASE_PATH

    def "取引リスト取得API: 正常系 店舗側が取引リストを取得できる"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop_table", {
            id | shop_id | number | capacity
            1  | 1       | 1      | 4
            2  | 1       | 2      | 8
        }
        TableHelper.insert sql, "transaction", {
            id | shop_id | table_id | code                | number_of_people
            1  | 1       | 1        | RandomHelper.uuid() | 4
            2  | 1       | 2        | RandomHelper.uuid() | 8
        }
        TableHelper.insert sql, "order", {
            id | transaction_id | ordered_date
            1  | 1              | DateHelper.today()
            2  | 1              | DateHelper.today()
            3  | 2              | DateHelper.today()
        }
        TableHelper.insert sql, "menu", {
            id | shop_id | name | price | image_url
            1  | 1       | ""   | 100   | ""
            2  | 1       | ""   | 200   | ""
            3  | 1       | ""   | 300   | ""
        }
        TableHelper.insert sql, "order_menu", {
            order_id | menu_id | quantity | status
            1        | 1       | 3        | 0
            2        | 1       | 4        | 0
            2        | 2       | 5        | 0
            3        | 1       | 6        | 0
            3        | 2       | 7        | 0
            3        | 3       | 8        | 0
        }
        // @formatter:on

        when:
        final request = this.getRequest(String.format(GET_TRANSACTIONS_PATH, 1))
        final response = this.execute(request, HttpStatus.OK, TransactionsResponse)

        then:
        response.transactions*.id == [1, 2]
        response.transactions*.tableId == [1, 2]
        response.transactions*.tableNumber == [1, 2]
        response.transactions*.orders*.id == [[1, 2], [3]]
        response.transactions*.orders*.menus*.id == [[[1], [1, 2]], [[1, 2, 3]]]
        response.transactions*.orders*.menus*.price == [[[100], [100, 200]], [[100, 200, 300]]]
        response.transactions*.orders*.menus*.quantity == [[[3], [4, 5]], [[6, 7, 8]]]
    }

    def "取引リスト取得API: 異常系 自分以外の店舗IDの場合は403エラー"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            2  | ""   | ""   | ""
        }
        // @formatter:on

        expect:
        final request = this.getRequest(String.format(GET_TRANSACTIONS_PATH, 2))
        this.execute(request, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "取引取得API: 正常系 店舗側が取引を取得できる"() {
        given:
        this.loginShop();

        // @formatter:off
        TableHelper.insert sql, "shop_table", {
            id | shop_id | number | capacity
            1  | 1       | 1      | 4
        }
        TableHelper.insert sql, "transaction", {
            id | shop_id | table_id | code                | number_of_people
            1  | 1       | 1        | RandomHelper.uuid() | 4
        }
        TableHelper.insert sql, "order", {
            id | transaction_id | ordered_date
            1  | 1              | DateHelper.today()
            2  | 1              | DateHelper.today()
        }
        TableHelper.insert sql, "menu", {
            id | shop_id | name | price | image_url
            1  | 1       | ""   | 100   | ""
            2  | 1       | ""   | 200   | ""
        }
        TableHelper.insert sql, "order_menu", {
            order_id | menu_id | quantity | status
            1        | 1       | 3        | 0
            2        | 1       | 4        | 0
            2        | 2       | 5        | 0
        }
        // @formatter:on

        when:
        final request = this.getRequest(String.format(GET_TRANSACTION_PATH, 1, 1))
        final response = this.execute(request, HttpStatus.OK, TransactionResponse)

        then:
        response.id == 1
        response.tableId == 1
        response.tableNumber == 1
        response.orders*.id == [1, 2]
        response.orders*.id == [1, 2]
        response.orders*.menus*.id == [[1], [1, 2]]
        response.orders*.menus*.price == [[100], [100, 200]]
        response.orders*.menus*.quantity == [[3], [4, 5]]
        Objects.nonNull(response.shopName)
    }

    def "取引取得API: 異常系 自分以外の店舗IDの場合は403エラー"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            2  | ""   | ""   | ""
        }
        // @formatter:on

        expect:
        final request = this.getRequest(String.format(GET_TRANSACTIONS_PATH, 2))
        this.execute(request, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "取引取得API: 異常系 店舗が存在しない場合は404エラー"() {
        given:
        this.loginShop()

        expect:
        final request = this.getRequest(String.format(GET_TRANSACTIONS_PATH, 2))
        this.execute(request, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "取引取得API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.getRequest(String.format(GET_TRANSACTIONS_PATH, 1))
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

    @Unroll
    def "取引取得API: 異常系 店舗/取引が存在しない場合は404エラー"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop_table", {
            id | shop_id | number | capacity
            1  | 1       | 1      | 4
        }
        TableHelper.insert sql, "transaction", {
            id | shop_id | table_id | code                | number_of_people
            1  | 1       | 1        | RandomHelper.uuid() | 4
        }
        // @formatter:on

        expect:
        final request = this.getRequest(String.format(GET_TRANSACTION_PATH, inputShopId, inputTransactionId))
        this.execute(request, new NotFoundException(expectedErrorCode))

        where:
        inputShopId | inputTransactionId || expectedErrorCode
        2           | 1                  || ErrorCode.NOT_FOUND_SHOP
        1           | 2                  || ErrorCode.NOT_FOUND_TRANSACTION
    }

    def "取引取得API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.getRequest(String.format(GET_TRANSACTION_PATH, 1, 1))
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

    def "取引削除API: 正常系 店舗側が取引を削除できる"() {
        given:
        this.loginShop();

        // @formatter:off
        TableHelper.insert sql, "shop_table", {
            id | shop_id | number | capacity
            1  | 1       | 1      | 4
        }
        TableHelper.insert sql, "transaction", {
            id | shop_id | table_id | code                | number_of_people
            1  | 1       | 1        | RandomHelper.uuid() | 4
        }
        TableHelper.insert sql, "order", {
            id | transaction_id | ordered_date
            1  | 1              | DateHelper.today()
        }
        TableHelper.insert sql, "menu", {
            id | shop_id | name | price | image_url
            1  | 1       | ""   | 100   | ""
        }
        TableHelper.insert sql, "order_menu", {
            order_id | menu_id | quantity | status
            1        | 1       | 3        | 0
        }
        // @formatter:on

        when:
        final request = this.deleteRequest(String.format(DELETE_TRANSACTION_PATH, 1, 1))
        final response = this.execute(request, HttpStatus.OK)

        then:
        sql.firstRow("SELECT * FROM transaction") == null
        sql.firstRow("SELECT  * FROM `order`") == null
        sql.firstRow("SELECT * FROM order_menu") == null
    }

    def "取引削除API: 異常系 自分以外の店舗IDの場合は403エラー"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            2  | ""   | ""   | ""
        }
        // @formatter:on

        expect:
        final request = this.deleteRequest(String.format(DELETE_TRANSACTION_PATH, 2, 1))
        this.execute(request, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    @Unroll
    def "取引取得API: 異常系 店舗/取引が存在しない場合は404エラー"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop_table", {
            id | shop_id | number | capacity
            1  | 1       | 1      | 4
        }
        TableHelper.insert sql, "transaction", {
            id | shop_id | table_id | code                | number_of_people
            1  | 1       | 1        | RandomHelper.uuid() | 4
        }
        // @formatter:on

        expect:
        final request = this.deleteRequest(String.format(DELETE_TRANSACTION_PATH, inputShopId, inputTransactionId))
        this.execute(request, new NotFoundException(expectedErrorCode))

        where:
        inputShopId | inputTransactionId || expectedErrorCode
        2           | 1                  || ErrorCode.NOT_FOUND_SHOP
        1           | 2                  || ErrorCode.NOT_FOUND_TRANSACTION
    }

    def "取引削除API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.deleteRequest(String.format(DELETE_TRANSACTION_PATH, 1, 1))
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

    def "全取引削除API: 正常系 店舗側が全ての取引を削除できる"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop_table", {
            id | shop_id | number | capacity
            1  | 1       | 1      | 4
            2  | 1       | 2      | 8
        }
        TableHelper.insert sql, "transaction", {
            id | shop_id | table_id | code                | number_of_people
            1  | 1       | 1        | RandomHelper.uuid() | 4
            2  | 1       | 2        | RandomHelper.uuid() | 8
        }
        TableHelper.insert sql, "order", {
            id | transaction_id | ordered_date
            1  | 1              | DateHelper.today()
            2  | 1              | DateHelper.today()
            3  | 2              | DateHelper.today()
        }
        TableHelper.insert sql, "menu", {
            id | shop_id | name | price | image_url
            1  | 1       | ""   | 100   | ""
            2  | 1       | ""   | 200   | ""
            3  | 1       | ""   | 300   | ""
        }
        TableHelper.insert sql, "order_menu", {
            order_id | menu_id | quantity | status
            1        | 1       | 3        | 0
            2        | 1       | 4        | 0
            2        | 2       | 5        | 0
            3        | 1       | 6        | 0
            3        | 2       | 7        | 0
            3        | 3       | 8        | 0
        }
        // @formatter:on

        when:
        final request = this.deleteRequest(String.format(DELETE_ALL_TRANSACTION_PATH, 1))
        final response = this.execute(request, HttpStatus.OK)

        then:
        sql.firstRow("SELECT * FROM transaction") == null
        sql.firstRow("SELECT  * FROM `order`") == null
        sql.firstRow("SELECT * FROM order_menu") == null
    }

    def "全取引削除API: 異常系 自分以外の店舗IDの場合は403エラー"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            2  | ""   | ""   | ""
        }
        // @formatter:on

        expect:
        final request = this.deleteRequest(String.format(DELETE_ALL_TRANSACTION_PATH, 2))
        this.execute(request, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "全取引削除API: 異常系 店舗が存在しない場合は404エラー"() {
        given:
        this.loginShop()

        expect:
        final request = this.deleteRequest(String.format(DELETE_ALL_TRANSACTION_PATH, 2))
        this.execute(request, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "全取引削除API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.deleteRequest(String.format(DELETE_ALL_TRANSACTION_PATH, 1))
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

}

package cc.rits.openhacku2022.api.controller

import cc.rits.openhacku2022.api.request.OrderCreateRequest
import cc.rits.openhacku2022.enums.OrderStatusEnum
import cc.rits.openhacku2022.exception.*
import cc.rits.openhacku2022.helper.TableHelper
import org.springframework.http.HttpStatus
import spock.lang.Shared

/**
 * OrderRestController_Itの統合テスト
 */
class OrderRestController_IT extends BaseRestController_IT {

    // API PATH
    static final String BASE_PATH = "/api/shops/%d/orders"
    static final String CREATE_ORDER_PATH = BASE_PATH

    @Shared
    OrderCreateRequest orderCreateRequest

    def setup() {
        final orderMenus = [
            OrderCreateRequest.OrderMenuRequest.builder()
                .menuId(1)
                .quantity(1)
                .build(),
            OrderCreateRequest.OrderMenuRequest.builder()
                .menuId(2)
                .quantity(3)
                .build(),
        ]
        this.orderCreateRequest = OrderCreateRequest.builder()
            .menus(orderMenus)
            .build()
    }

    def "注文作成API: 正常系 注文を作成"() {
        given:
        final transaction = this.login()

        // @formatter:off
        TableHelper.insert sql, "menu", {
            id | shop_id | name | price | image_url
            1  | 1       | ""   | 1000  | ""
            2  | 1       | ""   | 1000  | ""
        }
        // @formatter:on

        when:
        final request = this.postRequest(String.format(CREATE_ORDER_PATH, 1), this.orderCreateRequest)
        this.execute(request, HttpStatus.OK)

        then:
        final createdOrder = sql.firstRow("SELECT * FROM `order`")
        createdOrder.transaction_id == transaction.id
        final createdOrderMenus = sql.rows("SELECT * FROM order_menu")
        createdOrderMenus*.order_id == this.orderCreateRequest.menus.collect { createdOrder.id }
        createdOrderMenus*.menu_id == this.orderCreateRequest.menus*.menuId
        createdOrderMenus*.quantity == this.orderCreateRequest.menus*.quantity
        createdOrderMenus*.status == this.orderCreateRequest.menus.collect { OrderStatusEnum.ACCEPTED.id }
    }

    def "注文作成API: 異常系 リクエストボディのバリデーション"() {
        given:
        this.login()

        this.orderCreateRequest.menus = isMenusEmpty
            ? []
            : [OrderCreateRequest.OrderMenuRequest.builder()
                   .menuId(1)
                   .quantity(inputQuantity)
                   .build()]

        expect:
        final request = this.postRequest(String.format(CREATE_ORDER_PATH, 1), this.orderCreateRequest)
        this.execute(request, new BadRequestException(expectedErrorCode))

        where:
        isMenusEmpty | inputQuantity || expectedErrorCode
        true         | 1             || ErrorCode.ORDER_MENUS_MUST_NOT_BE_EMPTY
        false        | 0             || ErrorCode.INVALID_ORDER_MENU_QUANTITY
    }

    def "注文作成API: 異常系 メニューが存在しない場合は404エラー"() {
        given:
        this.login()

        expect:
        final request = this.postRequest(String.format(CREATE_ORDER_PATH, 1), this.orderCreateRequest)
        this.execute(request, new NotFoundException(ErrorCode.NOT_FOUND_MENU))
    }

    def "取引取得API: 異常系 取引中の店舗でない場合は403エラー"() {
        given:
        this.login()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            2  | ""   | ""   | ""
        }
        // @formatter:on

        expect:
        final request = this.postRequest(String.format(CREATE_ORDER_PATH, 2), this.orderCreateRequest)
        this.execute(request, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "取引取得API: 異常系 店舗が存在しない場合は404エラー"() {
        given:
        this.login()

        expect:
        final request = this.postRequest(String.format(CREATE_ORDER_PATH, 2), this.orderCreateRequest)
        this.execute(request, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "注文作成API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.postRequest(String.format(CREATE_ORDER_PATH, 1), this.orderCreateRequest)
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

}

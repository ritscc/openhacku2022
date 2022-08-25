package cc.rits.openhacku2022.api.controller.admin

import cc.rits.openhacku2022.api.controller.BaseRestController_IT
import cc.rits.openhacku2022.api.request.OrderStatusUpdateRequest
import cc.rits.openhacku2022.enums.OrderStatusEnum
import cc.rits.openhacku2022.exception.*
import cc.rits.openhacku2022.helper.DateHelper
import cc.rits.openhacku2022.helper.RandomHelper
import cc.rits.openhacku2022.helper.TableHelper
import org.springframework.http.HttpStatus
import spock.lang.Shared
import spock.lang.Unroll

/**
 * AdminOrderRestControllerの統合テスト
 */
class AdminOrderRestController_IT extends BaseRestController_IT {
    // API PATH
    static final String BASE_PATH = "/api/admin/shops/%d/orders/%d"
    static final String PUT_ORDER_STATUS_PATH = BASE_PATH + "/menus/%d"

    @Shared
    OrderStatusUpdateRequest orderStatusUpdateRequest

    def setup() {
        this.orderStatusUpdateRequest = OrderStatusUpdateRequest.builder() //
            .status(OrderStatusEnum.ACCEPTED.id) //
            .build()
    }

    def "注文ステータス更新API: 店舗側が注文ステータスを更新できる"() {
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
            1        | 1       | 3        | OrderStatusEnum.ACCEPTED.id
        }
        // @formatter:on

        when:
        final request = this.putRequest(String.format(PUT_ORDER_STATUS_PATH, 1, 1, 1,), orderStatusUpdateRequest)
        this.execute(request, HttpStatus.OK)

        then:
        final result = sql.firstRow("SELECT * FROM order_menu")
        result.status == orderStatusUpdateRequest.status
    }

    def "注文ステータス更新API: 異常系 リクエストボディのバリデーション"() {
        given:
        this.loginShop()

        orderStatusUpdateRequest.setStatus(-1)

        expect:
        final request = this.putRequest(String.format(PUT_ORDER_STATUS_PATH, 1, 1, 1,), orderStatusUpdateRequest)
        this.execute(request, new BadRequestException(ErrorCode.INVALID_ORDER_STATUS))
    }

    def "注文ステータス更新API: 異常系 自分以外の店舗IDの場合は403エラー"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            2  | ""   | ""   | ""
        }
        // @formatter:on

        expect:
        final request = this.putRequest(String.format(PUT_ORDER_STATUS_PATH, 2, 1, 1,), orderStatusUpdateRequest)
        this.execute(request, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    @Unroll
    def "注文ステータス更新API: 異常系 店舗/注文/注文メニューが存在しない場合は404エラー"() {
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
            1        | 1       | 3        | OrderStatusEnum.ACCEPTED.id
        }
        // @formatter:on

        expect:
        final request = this.putRequest(String.format(PUT_ORDER_STATUS_PATH, inputShopId, inputOrderId, inputMenuId), orderStatusUpdateRequest)
        this.execute(request, new NotFoundException(expecteErrorCode))

        where:
        inputShopId | inputOrderId | inputMenuId || expecteErrorCode
        2           | 1            | 1           || ErrorCode.NOT_FOUND_SHOP
        1           | 2            | 1           || ErrorCode.NOT_FOUND_ORDER
        1           | 1            | 2           || ErrorCode.NOT_FOUND_ORDER_MENU
    }

    def "注文ステータス更新API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.putRequest(String.format(PUT_ORDER_STATUS_PATH, 1, 1, 1,), orderStatusUpdateRequest)
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

}

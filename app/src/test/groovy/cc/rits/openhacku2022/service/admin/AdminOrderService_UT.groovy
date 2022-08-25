package cc.rits.openhacku2022.service.admin

import cc.rits.openhacku2022.BaseSpecification
import cc.rits.openhacku2022.api.request.OrderStatusUpdateRequest
import cc.rits.openhacku2022.enums.OrderStatusEnum
import cc.rits.openhacku2022.exception.BaseException
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.ForbiddenException
import cc.rits.openhacku2022.exception.NotFoundException
import cc.rits.openhacku2022.helper.RandomHelper
import cc.rits.openhacku2022.model.OrderMenuModel
import cc.rits.openhacku2022.model.OrderModel
import cc.rits.openhacku2022.model.ShopModel
import cc.rits.openhacku2022.repository.OrderRepository
import cc.rits.openhacku2022.repository.ShopRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired

/**
 * AdminOrderService_UTの単体テスト
 */
class AdminOrderService_UT extends BaseSpecification {

    @Autowired
    AdminOrderService sut

    @SpringBean
    ShopRepository shopRepository = Mock()

    @SpringBean
    OrderRepository orderRepository = Mock()

    def "updateOrderStatus: 注文ステータスを更新"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final order = Spy(OrderModel)
        final orderMenu = Spy(OrderMenuModel)

        final requestBody = OrderStatusUpdateRequest.builder()
            .status(OrderStatusEnum.COMPLETED.id)
            .build()

        when:
        this.sut.updateOrderStatus(shop.id, order.id, orderMenu.menuId, requestBody, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.orderRepository.selectByOrderId(order.id) >> Optional.of(order)
        1 * order.getOrderMenu(orderMenu.menuId) >> Optional.of(orderMenu)
        1 * orderMenu.setStatus(OrderStatusEnum.find(requestBody.getStatus()))
        1 * this.orderRepository.updateOrderMenu(order.id, orderMenu)
    }

    def "updateOrderStatus: 店舗が存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final order = Spy(OrderModel)
        final orderMenu = Spy(OrderMenuModel)

        final requestBody = RandomHelper.mock(OrderStatusUpdateRequest)

        when:
        this.sut.updateOrderStatus(shop.id, order.id, orderMenu.menuId, requestBody, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "updateOrderStatus: ログイン店舗以外の場合は403エラー"() {
        given:
        final shop = Spy(ShopModel)
        final order = Spy(OrderModel)
        final orderMenu = Spy(OrderMenuModel)

        final requestBody = RandomHelper.mock(OrderStatusUpdateRequest)

        when:
        this.sut.updateOrderStatus(1, order.id, orderMenu.menuId, requestBody, shop)

        then:
        1 * this.shopRepository.selectById(1) >> Optional.of(shop)
        1 * shop.id >> 2
        final BaseException exception = thrown()
        verifyException(exception, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "updateOrderStatus: 注文が存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final order = Spy(OrderModel)
        final orderMenu = Spy(OrderMenuModel)

        final requestBody = RandomHelper.mock(OrderStatusUpdateRequest)

        when:
        this.sut.updateOrderStatus(shop.id, order.id, orderMenu.menuId, requestBody, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.orderRepository.selectByOrderId(order.id) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_ORDER))
    }

    def "updateOrderStatus: 注文メニューが存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final order = Spy(OrderModel)
        final orderMenu = Spy(OrderMenuModel)

        final requestBody = RandomHelper.mock(OrderStatusUpdateRequest)

        when:
        this.sut.updateOrderStatus(shop.id, order.id, orderMenu.menuId, requestBody, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.orderRepository.selectByOrderId(order.id) >> Optional.of(order)
        1 * order.getOrderMenu(orderMenu.menuId) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_ORDER_MENU))
    }
}

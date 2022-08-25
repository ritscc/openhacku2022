package cc.rits.openhacku2022.service

import cc.rits.openhacku2022.BaseSpecification
import cc.rits.openhacku2022.client.CheckoutClient
import cc.rits.openhacku2022.enums.OrderStatusEnum
import cc.rits.openhacku2022.exception.BadRequestException
import cc.rits.openhacku2022.exception.BaseException
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.helper.RandomHelper
import cc.rits.openhacku2022.model.OrderMenuModel
import cc.rits.openhacku2022.model.OrderModel
import cc.rits.openhacku2022.model.TransactionModel
import cc.rits.openhacku2022.query_service.TransactionQueryService
import cc.rits.openhacku2022.query_service.dto.TransactionWithOrderDto
import cc.rits.openhacku2022.repository.OrderRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired

/**
 * TransactionServiceの単体テスト
 */
class TransactionService_UT extends BaseSpecification {

    @Autowired
    TransactionService sut

    @SpringBean
    TransactionQueryService transactionQueryService = Mock()

    @SpringBean
    OrderRepository orderRepository = Mock()

    @SpringBean
    CheckoutClient checkoutClient = Mock()

    def "getTransaction: 取引を取得"() {
        given:
        final transaction = RandomHelper.mock(TransactionModel)
        final transactionWithOrderDto = RandomHelper.mock(TransactionWithOrderDto)

        when:
        final result = this.sut.getTransaction(transaction)

        then:
        1 * this.transactionQueryService.getTransaction(transaction.shopId, transaction.id) >> transactionWithOrderDto
        result == transactionWithOrderDto
    }

    def "getCheckoutUrl: 支払いURLを取得"() {
        given:
        final transaction = RandomHelper.mock(TransactionModel)
        final orderMenus = [
            OrderMenuModel.builder()
                .status(OrderStatusEnum.COMPLETED)
                .quantity(2)
                .price(1000)
                .build(),
            OrderMenuModel.builder()
                .status(OrderStatusEnum.COMPLETED)
                .quantity(1)
                .price(1400)
                .build(),
        ]
        final orders = [
            OrderModel.builder()
                .menus(orderMenus)
                .build()
        ]

        final checkoutUrl = RandomHelper.alphanumeric(255)

        when:
        final result = this.sut.getCheckoutUrl(transaction)

        then:
        1 * this.orderRepository.selectByTransactionId(transaction.id) >> orders
        1 * this.checkoutClient.send(1000 * 2 + 1400 * 1) >> checkoutUrl
        result == checkoutUrl
    }

    def "getCheckoutUrl: 未完了の注文があれば400エラー"() {
        given:
        final transaction = RandomHelper.mock(TransactionModel)
        final orderMenus = [
            OrderMenuModel.builder()
                .status(OrderStatusEnum.IN_PREPARATION)
                .quantity(2)
                .price(1000)
                .build(),
        ]
        final orders = [
            OrderModel.builder()
                .menus(orderMenus)
                .build()
        ]

        when:
        this.sut.getCheckoutUrl(transaction)

        then:
        1 * this.orderRepository.selectByTransactionId(transaction.id) >> orders
        final BaseException exception = thrown()
        verifyException(exception, new BadRequestException(ErrorCode.ORDERS_ARE_NOT_COMPLETED))
    }

    def "getCheckoutUrl: 未注文の場合は400エラー"() {
        given:
        final transaction = RandomHelper.mock(TransactionModel)
        final orders = []

        when:
        this.sut.getCheckoutUrl(transaction)

        then:
        1 * this.orderRepository.selectByTransactionId(transaction.id) >> orders
        final BaseException exception = thrown()
        verifyException(exception, new BadRequestException(ErrorCode.ORDERS_ARE_NOT_COMPLETED))
    }

}

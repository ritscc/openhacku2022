package cc.rits.openhacku2022.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.client.PaymentClient;
import cc.rits.openhacku2022.enums.OrderStatusEnum;
import cc.rits.openhacku2022.exception.BadRequestException;
import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.query_service.TransactionQueryService;
import cc.rits.openhacku2022.query_service.dto.TransactionWithOrderDto;
import cc.rits.openhacku2022.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

/**
 * トランザクションサービス
 */
@RequiredArgsConstructor
@Service
@Transactional
public class TransactionService {

    private final TransactionQueryService transactionQueryService;

    private final OrderRepository orderRepository;

    private final PaymentClient paymentClient;

    /**
     * 取引を取得
     *
     * @param transaction 取引
     * @return 取引
     */
    public TransactionWithOrderDto getTransaction(final TransactionModel transaction) {
        return this.transactionQueryService.getTransaction(transaction.getShopId(), transaction.getId());
    }

    /**
     * 支払いURLを取得
     *
     * @param transaction 取引
     * @return 支払いURL
     */
    public String getCheckoutUrl(final TransactionModel transaction) {
        // 注文リストを取得
        final var orders = this.orderRepository.selectByTransactionId(transaction.getId());

        // 注文をしているかどうか確認
        if (orders.isEmpty()) {
            throw new BadRequestException(ErrorCode.ORDERS_ARE_NOT_COMPLETED);
        }

        // 支払い金額を取得
        final var paymentAmount = orders.stream() //
            .flatMap(orderModel -> orderModel.getMenus().stream()) //
            .map(orderMenu -> {
                if (orderMenu.getStatus() != OrderStatusEnum.COMPLETED) {
                    throw new BadRequestException(ErrorCode.ORDERS_ARE_NOT_COMPLETED);
                }
                return orderMenu.getQuantity() * orderMenu.getPrice();
            }) //
            .mapToLong(amount -> amount) //
            .sum();

        return paymentClient.send(paymentAmount);
    }

}

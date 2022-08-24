package cc.rits.openhacku2022.service;

import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.client.CheckoutClient;
import cc.rits.openhacku2022.enums.OrderStatusEnum;
import cc.rits.openhacku2022.exception.BadRequestException;
import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.query_service.TransactionQueryService;
import cc.rits.openhacku2022.query_service.dto.TransactionWithOrderDto;
import cc.rits.openhacku2022.repository.OrderRepository;
import cc.rits.openhacku2022.repository.TransactionRepository;
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

    private final TransactionRepository transactionRepository;

    private final FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    private final CheckoutClient checkoutClient;

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

        return checkoutClient.send(paymentAmount);
    }

    /**
     * 期限切れの取引を削除
     */
    public void deleteExpiredTransaction() {
        final var transactions = this.transactionRepository.selectAll();
        transactions.stream() //
            .filter(transaction -> this.sessionRepository.findByPrincipalName(transaction.getCode()).isEmpty()) //
            .forEach(this.transactionRepository::delete);
    }

}

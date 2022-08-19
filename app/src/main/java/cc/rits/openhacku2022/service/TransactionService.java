package cc.rits.openhacku2022.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.query_service.TransactionQueryService;
import cc.rits.openhacku2022.query_service.dto.TransactionWithOrderDto;
import cc.rits.openhacku2022.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

/**
 * トランザクションサービス
 */
@RequiredArgsConstructor
@Service
@Transactional
public class TransactionService {

    private final TransactionQueryService transactionQueryService;

    private final ShopRepository shopRepository;

    /**
     * 取引を取得
     *
     * @param transaction 取引
     * @return 取引
     */
    public TransactionWithOrderDto getTransaction(final TransactionModel transaction) {
        return this.transactionQueryService.getTransaction(transaction.getShopId(), transaction.getId());
    }

}

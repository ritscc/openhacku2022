package cc.rits.openhacku2022.query_service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.db.mapper.TransactionMapper;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.query_service.dto.TransactionWithOrderDto;
import cc.rits.openhacku2022.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

/**
 * トランザクションクエリサービス
 */
@RequiredArgsConstructor
@Service
@Transactional
public class TransactionQueryService {

    private final ShopRepository shopRepository;

    private final TransactionMapper transactionMapper;

    /**
     * 取引を取得
     * 
     * @param shopId 店舗ID
     * @param transaction 取引
     * @return 取引
     */
    public TransactionWithOrderDto getTransaction(final Integer shopId, final TransactionModel transaction) {
        return new TransactionWithOrderDto(this.transactionMapper.selectByIdAndShopId(transaction.getId(), shopId));
    }

}

package cc.rits.openhacku2022.query_service;

import cc.rits.openhacku2022.db.mapper.TransactionMapper;
import cc.rits.openhacku2022.model.TransactionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * トランザクションクエリサービス
 */
@RequiredArgsConstructor
@Service
@Transactional
public class TransactionQueryService {

    private final TransactionMapper transactionMapper;

    public void getTransaction(final Integer shopId, final TransactionModel transaction) {

    }

}

package cc.rits.openhacku2022.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import cc.rits.openhacku2022.db.entity.TransactionExample;
import cc.rits.openhacku2022.db.mapper.TransactionMapper;
import cc.rits.openhacku2022.model.TransactionModel;
import lombok.RequiredArgsConstructor;

/**
 * 取引リポジトリ
 */
@Repository
@RequiredArgsConstructor
public class TransactionRepository {

    private final TransactionMapper transactionMapper;

    public Optional<TransactionModel> selectByCode(final String code) {
        final var example = new TransactionExample();
        example.createCriteria().andCodeEqualTo(code);
        return this.transactionMapper.selectByExample(example).stream() //
            .map(TransactionModel::new) //
            .findFirst();
    }
}

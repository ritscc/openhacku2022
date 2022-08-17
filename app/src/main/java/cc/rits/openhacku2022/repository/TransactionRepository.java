package cc.rits.openhacku2022.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import cc.rits.openhacku2022.db.entity.TransactionExample;
import cc.rits.openhacku2022.db.mapper.TransactionMapper;
import cc.rits.openhacku2022.factory.TransactionFactory;
import cc.rits.openhacku2022.model.TransactionModel;
import lombok.RequiredArgsConstructor;

/**
 * 取引リポジトリ
 */
@Repository
@RequiredArgsConstructor
public class TransactionRepository {

    private final TransactionMapper transactionMapper;

    private final TransactionFactory transactionFactory;

    /**
     * 取引コードから取引を取得
     * 
     * @param code 取引コード
     * @return 取引
     */
    public Optional<TransactionModel> selectByCode(final String code) {
        final var example = new TransactionExample();
        example.createCriteria().andCodeEqualTo(code);
        return this.transactionMapper.selectByExample(example).stream() //
            .map(TransactionModel::new) //
            .findFirst();
    }

    /**
     * 取引を作成
     * 
     * @param transactionModel 取引
     */
    public void insert(final TransactionModel transactionModel) {
        final var transaction = this.transactionFactory.createTransaction(transactionModel);
        this.transactionMapper.insertSelective(transaction);
    }

    /**
     * 取引を削除
     * 
     * @param transactionModel 取引
     */
    public void delete(final TransactionModel transactionModel) {
        this.transactionMapper.deleteByPrimaryKey(transactionModel.getId());
    }

}

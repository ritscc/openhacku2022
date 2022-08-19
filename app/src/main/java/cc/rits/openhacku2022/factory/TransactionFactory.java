package cc.rits.openhacku2022.factory;

import org.springframework.stereotype.Component;

import cc.rits.openhacku2022.db.entity.Transaction;
import cc.rits.openhacku2022.model.TransactionModel;

/**
 * クーポンファクトリ
 */
@Component
public class TransactionFactory {

    /**
     * 取引を作成
     * 
     * @param transactionModel model
     * @return entity
     */
    public Transaction createTransaction(final TransactionModel transactionModel) {
        return Transaction.builder() //
            .id(transactionModel.getId()) //
            .shopId(transactionModel.getShopId()) //
            .tableId(transactionModel.getTableId()) //
            .code(transactionModel.getCode()) //
            .numberOfPeople(transactionModel.getNumberOfPeople()) //
            .build();
    }

}

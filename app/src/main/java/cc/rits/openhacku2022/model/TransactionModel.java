package cc.rits.openhacku2022.model;

import java.util.UUID;

import cc.rits.openhacku2022.db.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取引モデル
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionModel {

    /**
     * 取引ID
     */
    private Integer id;
    /**
     * 店舗ID
     */
    private Integer shopId;
    /**
     * テーブルID
     */
    private Integer tableId;
    /**
     * 取引コード
     */
    @Builder.Default
    private String code = UUID.randomUUID().toString();
    /**
     * 利用人数
     */
    private Integer numberOfPeople;

    public TransactionModel(final Transaction transaction) {
        this.id = transaction.getId();
        this.shopId = transaction.getShopId();
        this.tableId = transaction.getTableId();
        this.code = transaction.getCode();
        this.numberOfPeople = transaction.getNumberOfPeople();
    }

}

package cc.rits.openhacku2022.model;

import java.util.Objects;

import cc.rits.openhacku2022.db.entity.join.ShopTableWithTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * テーブルモデル
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableModel {

    /**
     * テーブルID
     */
    private Integer id;

    /**
     * 店舗ID
     */
    private Integer shopId;

    /**
     * テーブル番号
     */
    private Integer tableNumber;

    /**
     * 座れる人数
     */
    private Integer capacity;

    /**
     * 利用中フラグ
     */
    private Boolean isUsed;

    public TableModel(final ShopTableWithTransaction shopTable) {
        this.id = shopTable.getId();
        this.shopId = shopTable.getShopId();
        this.tableNumber = shopTable.getNumber();
        this.capacity = shopTable.getCapacity();
        this.isUsed = Objects.nonNull(shopTable.getTransactionId());
    }

    /**
     * テーブルが利用可能かチェック
     * 
     * @param numberOfPeople 利用人数
     * @return 利用可能かどうか
     */
    public boolean available(final Integer numberOfPeople) {
        return !this.getIsUsed() && this.getCapacity() >= numberOfPeople;
    }

}

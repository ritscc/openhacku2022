package cc.rits.openhacku2022.db.entity.join;

import java.util.List;

import cc.rits.openhacku2022.db.entity.Shop;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 店舗 + テーブル
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopWithTables extends Shop {

    /**
     * テーブルリスト
     */
    List<ShopTableWithTransaction> tables;

}

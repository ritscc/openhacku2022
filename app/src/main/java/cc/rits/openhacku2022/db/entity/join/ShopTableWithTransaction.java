package cc.rits.openhacku2022.db.entity.join;

import cc.rits.openhacku2022.db.entity.ShopTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * テーブル + 取引
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopTableWithTransaction extends ShopTable {

    /**
     * 取引ID
     */
    Integer transactionId;

}

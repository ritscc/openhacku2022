package cc.rits.openhacku2022.db.entity.join;

import java.util.List;

import cc.rits.openhacku2022.db.entity.Table;
import cc.rits.openhacku2022.db.entity.Transaction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 取引 + 注文リスト
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TransactionWithShopNameAndOrdersAndTable extends Transaction {

    /**
     * 店舗名
     */
    String shopName;

    /**
     * 注文リスト
     */
    List<OrderWithOrderMenus> orders;

    /**
     * テーブル
     */
    Table table;

}

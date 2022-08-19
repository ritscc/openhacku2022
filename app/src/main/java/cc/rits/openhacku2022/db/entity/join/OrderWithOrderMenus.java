package cc.rits.openhacku2022.db.entity.join;

import java.util.List;

import cc.rits.openhacku2022.db.entity.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 注文 + メニューリスト
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderWithOrderMenus extends Order {

    /**
     * メニューリスト
     */
    List<OrderMenuWitMenu> orderMenus;

}

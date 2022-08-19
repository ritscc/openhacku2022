package cc.rits.openhacku2022.db.entity.join;

import cc.rits.openhacku2022.db.entity.Menu;
import cc.rits.openhacku2022.db.entity.OrderMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * メニュー + 注文数
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderMenuWitMenu extends OrderMenu {

    /**
     * メニュー
     */
    Menu menu;

}

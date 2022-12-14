package cc.rits.openhacku2022.model;

import cc.rits.openhacku2022.db.entity.join.OrderMenuWitMenu;
import cc.rits.openhacku2022.enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文メニューモデル
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMenuModel {

    /**
     * メニューID
     */
    private Integer menuId;

    /**
     * 価格
     */
    private Integer price;

    /**
     * 個数
     */
    private Integer quantity;

    /**
     * 注文ステータス
     */
    @Builder.Default
    private OrderStatusEnum status = OrderStatusEnum.ACCEPTED;

    public OrderMenuModel(final OrderMenuWitMenu orderMenu) {
        this.menuId = orderMenu.getMenuId();
        this.price = orderMenu.getMenu().getPrice();
        this.quantity = orderMenu.getQuantity();
        this.status = OrderStatusEnum.find(orderMenu.getStatus());
    }

}

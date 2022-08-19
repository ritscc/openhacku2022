package cc.rits.openhacku2022.query_service.dto;

import cc.rits.openhacku2022.db.entity.join.OrderMenuWitMenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文メニューDTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMenuDto {

    /**
     * メニューID
     */
    private Integer id;

    /**
     * メニュー名
     */
    private String name;

    /**
     * 価格
     */
    private Integer price;

    /**
     * 画像URL
     */
    private String imageUrl;

    /**
     * 注文数
     */
    private Integer quantity;

    /**
     * 注文ステータス
     */
    private Integer status;

    public OrderMenuDto(final OrderMenuWitMenu orderMenu) {
        this.id = orderMenu.getMenu().getId();
        this.name = orderMenu.getMenu().getName();
        this.price = orderMenu.getMenu().getPrice();
        this.imageUrl = orderMenu.getMenu().getImageUrl();
        this.quantity = orderMenu.getQuantity();
        this.status = orderMenu.getStatus();
    }

}

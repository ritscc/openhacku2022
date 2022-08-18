package cc.rits.openhacku2022.api.response;

import cc.rits.openhacku2022.db.entity.join.OrderMenuWitMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文メニューレスポンス
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMenuResponse {

    /**
     * メニュー
     */
    @Schema(required = true)
    MenuResponse menu;

    /**
     * 注文ID
     */
    @Schema(required = true)
    Integer orderId;

    /**
     * ステータス
     */
    @Schema(required = true)
    Integer status;

    /**
     * 注文数
     */
    @Schema(required = true)
    Integer quantity;

    public OrderMenuResponse(final OrderMenuWitMenu orderMenu) {
        this.menu = new MenuResponse(orderMenu.getMenu());
        this.orderId = orderMenu.getOrderId();
        this.status = orderMenu.getStatus();
        this.quantity = orderMenu.getQuantity();
    }
}

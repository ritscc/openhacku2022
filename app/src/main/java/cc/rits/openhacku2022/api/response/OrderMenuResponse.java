package cc.rits.openhacku2022.api.response;

import cc.rits.openhacku2022.query_service.dto.OrderMenuDto;
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
     * メニューID
     */
    @Schema(required = true)
    Integer id;

    /**
     * メニュー名
     */
    @Schema(required = true)
    String name;

    /**
     * 価格
     */
    @Schema(required = true)
    Integer price;

    /**
     * 画像URL
     */
    @Schema(required = true)
    String imageUrl;

    /**
     * 注文数
     */
    @Schema(required = true)
    Integer quantity;

    /**
     * ステータス
     */
    @Schema(required = true)
    Integer status;

    public OrderMenuResponse(final OrderMenuDto orderMenu) {
        this.id = orderMenu.getId();
        this.name = orderMenu.getName();
        this.price = orderMenu.getPrice();
        this.imageUrl = orderMenu.getImageUrl();
        this.status = orderMenu.getStatus();
        this.quantity = orderMenu.getQuantity();
    }
}

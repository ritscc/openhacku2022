package cc.rits.openhacku2022.api.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cc.rits.openhacku2022.query_service.dto.OrderWIthOrderMenuDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文レスポンス
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    /**
     * 注文ID
     */
    @Schema(required = true)
    Integer id;
    /**
     * 注文日
     */
    @Schema(required = true)
    Date orderedDate;

    /**
     * 注文メニューリスト
     */
    @Schema(required = true)
    List<OrderMenuResponse> menus;

    public OrderResponse(final OrderWIthOrderMenuDto order) {
        this.id = order.getId();
        this.orderedDate = order.getOrderedDate();
        this.menus = order.getMenus().stream() //
            .map(OrderMenuResponse::new) //
            .collect(Collectors.toList());
    }

}

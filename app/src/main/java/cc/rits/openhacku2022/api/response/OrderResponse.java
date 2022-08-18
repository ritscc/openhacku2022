package cc.rits.openhacku2022.api.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cc.rits.openhacku2022.db.entity.join.OrderWithOrderMenus;
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
     * 取引ID
     */
    @Schema(required = true)
    Integer transactionId;

    /**
     * 注文日
     */
    @Schema(required = true)
    Date orderedDate;

    /**
     * 注文メニューリスト
     */
    @Schema(required = true)
    List<OrderMenuResponse> orderMenus;

    public OrderResponse(final OrderWithOrderMenus order) {
        this.id = order.getId();
        this.transactionId = order.getTransactionId();
        this.orderedDate = order.getOrderedDate();
        this.orderMenus = order.getOrderMenus().stream() //
            .map(OrderMenuResponse::new) //
            .collect(Collectors.toList());
    }

}

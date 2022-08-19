package cc.rits.openhacku2022.model;

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
     * 個数
     */
    private Integer quantity;

    /**
     * 注文ステータス
     */
    @Builder.Default
    private OrderStatusEnum status = OrderStatusEnum.ACCEPTED;

}

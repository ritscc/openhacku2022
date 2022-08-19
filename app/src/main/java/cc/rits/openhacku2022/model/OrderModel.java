package cc.rits.openhacku2022.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文モデル
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {

    /**
     * 注文ID
     */
    private Integer id;

    /**
     * 取引ID
     */
    private Integer transactionId;

    /**
     * 注文日
     */
    @Builder.Default
    private Date orderedDate = new Date();

    /**
     * メニューリスト
     */
    private List<OrderMenuModel> menus;

}

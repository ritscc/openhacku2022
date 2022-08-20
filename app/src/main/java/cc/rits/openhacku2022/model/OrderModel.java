package cc.rits.openhacku2022.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import cc.rits.openhacku2022.db.entity.join.OrderWithOrderMenus;
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

    public OrderModel(final OrderWithOrderMenus order) {
        this.id = order.getId();
        this.transactionId = order.getTransactionId();
        this.orderedDate = order.getOrderedDate();
        this.menus = order.getOrderMenus().stream() //
            .map(OrderMenuModel::new) //
            .collect(Collectors.toList());
    }

    public Optional<OrderMenuModel> getOrderMenu(final Integer menuId) {
        return this.getMenus().stream() //
            .filter(orderMenuModel -> Objects.equals(orderMenuModel.getMenuId(), menuId)) //
            .findFirst();
    }

}

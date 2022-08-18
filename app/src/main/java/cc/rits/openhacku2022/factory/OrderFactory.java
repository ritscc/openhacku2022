package cc.rits.openhacku2022.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cc.rits.openhacku2022.db.entity.Order;
import cc.rits.openhacku2022.db.entity.OrderMenu;
import cc.rits.openhacku2022.model.OrderModel;

/**
 * 注文ファクトリ
 */
@Component
public class OrderFactory {

    /**
     * 注文を作成
     * 
     * @param orderModel model
     * @return entity
     */
    public Order createOrder(final OrderModel orderModel) {
        return Order.builder() //
            .id(orderModel.getId()) //
            .transactionId(orderModel.getTransactionId()) //
            .orderedDate(orderModel.getOrderedDate()) //
            .build();
    }

    /**
     * 注文メニューを作成
     * 
     * @param orderModel model
     * @return entities
     */
    public List<OrderMenu> createOrderMenus(final OrderModel orderModel) {
        return orderModel.getMenus().stream() //
            .map(orderMenuModel -> OrderMenu.builder() //
                .menuId(orderMenuModel.getMenuId()) //
                .orderId(orderModel.getId()) //
                .quantity(orderMenuModel.getQuantity()) //
                .status(orderMenuModel.getStatus().getId()) //
                .build() //
            ).collect(Collectors.toList());
    }

}

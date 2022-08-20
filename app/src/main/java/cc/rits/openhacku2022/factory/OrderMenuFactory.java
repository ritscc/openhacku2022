package cc.rits.openhacku2022.factory;

import org.springframework.stereotype.Component;

import cc.rits.openhacku2022.db.entity.OrderMenu;
import cc.rits.openhacku2022.model.OrderMenuModel;

/**
 * 注文メニューファクトリ
 */
@Component
public class OrderMenuFactory {

    /**
     * 注文メニューを作成
     *
     * @param orderId 注文ID
     * @param orderMenuModel model
     * @return entity
     */
    public OrderMenu createOrderMenu(final Integer orderId, final OrderMenuModel orderMenuModel) {
        return OrderMenu.builder() //
            .orderId(orderId) //
            .menuId(orderMenuModel.getMenuId()) //
            .quantity(orderMenuModel.getQuantity()) //
            .status(orderMenuModel.getStatus().getId()) //
            .build();
    }

}

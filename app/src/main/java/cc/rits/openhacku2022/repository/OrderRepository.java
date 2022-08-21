package cc.rits.openhacku2022.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import cc.rits.openhacku2022.db.mapper.OrderMapper;
import cc.rits.openhacku2022.db.mapper.OrderMenuMapper;
import cc.rits.openhacku2022.factory.OrderFactory;
import cc.rits.openhacku2022.factory.OrderMenuFactory;
import cc.rits.openhacku2022.model.OrderMenuModel;
import cc.rits.openhacku2022.model.OrderModel;
import lombok.RequiredArgsConstructor;

/**
 * 注文リポジトリ
 */
@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final OrderMapper orderMapper;

    private final OrderMenuMapper orderMenuMapper;

    private final OrderFactory orderFactory;

    private final OrderMenuFactory orderMenuFactory;

    /**
     * 注文を取得
     *
     * @param orderId 店舗ID
     * @return 注文
     */
    public Optional<OrderModel> selectByOrderId(final Integer orderId) {
        return this.orderMapper.selectByOrderId(orderId) //
            .map(OrderModel::new);
    }

    /**
     * 注文を作成
     * 
     * @param orderModel 注文
     */
    public void insert(final OrderModel orderModel) {
        final var order = this.orderFactory.createOrder(orderModel);
        this.orderMapper.insertSelective(order);
        orderModel.setId(order.getId());

        // NOTE: ハッカソンなのでN+1は無視
        final var orderMenus = this.orderFactory.createOrderMenus(orderModel);
        orderMenus.forEach(this.orderMenuMapper::insertSelective);
    }

    /**
     * 注文メニューを更新
     * 
     * @param orderId 注文ID
     * @param orderMenuModel 注文メニュー
     */
    public void updateOrderMenu(final Integer orderId, final OrderMenuModel orderMenuModel) {
        final var orderMenu = this.orderMenuFactory.createOrderMenu(orderId, orderMenuModel);
        this.orderMenuMapper.updateByPrimaryKeySelective(orderMenu);
    }

}

package cc.rits.openhacku2022.repository;

import org.springframework.stereotype.Repository;

import cc.rits.openhacku2022.db.mapper.OrderMapper;
import cc.rits.openhacku2022.db.mapper.OrderMenuMapper;
import cc.rits.openhacku2022.factory.OrderFactory;
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

}

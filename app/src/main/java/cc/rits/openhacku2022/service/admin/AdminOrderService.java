package cc.rits.openhacku2022.service.admin;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.api.request.OrderStatusUpdateRequest;
import cc.rits.openhacku2022.enums.OrderStatusEnum;
import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.ForbiddenException;
import cc.rits.openhacku2022.exception.NotFoundException;
import cc.rits.openhacku2022.model.ShopModel;
import cc.rits.openhacku2022.repository.OrderRepository;
import cc.rits.openhacku2022.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

/**
 * 注文サービス(管理者用)
 */
@RequiredArgsConstructor
@Service
@Transactional
public class AdminOrderService {

    private final ShopRepository shopRepository;

    private final OrderRepository orderRepository;

    /**
     * 注文ステータスを更新
     * 
     * @param shopId 店舗ID
     * @param orderId 注文ID
     * @param menuId メニューID
     * @param requestBody 注文ステータス更新API
     * @param shop 店舗
     */
    public void updateOrderStatus( //
        final Integer shopId, //
        final Integer orderId, //
        final Integer menuId, //
        final OrderStatusUpdateRequest requestBody, //
        final ShopModel shop //
    ) {
        // 店舗の存在チェック
        this.shopRepository.selectById(shopId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHOP));

        // ログイン中の店舗と店舗IDが一致するかチェック
        if (!Objects.equals(shop.getId(), shopId)) {
            throw new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION);
        }

        // 注文メニューを取得
        final var order = this.orderRepository.selectByOrderId(orderId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ORDER));
        final var orderMenu = order.getOrderMenu(menuId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ORDER_MENU));

        // 注文ステータスを更新
        orderMenu.setStatus(OrderStatusEnum.find(requestBody.getStatus()));
        this.orderRepository.updateOrderMenu(orderId, orderMenu);
    }

}

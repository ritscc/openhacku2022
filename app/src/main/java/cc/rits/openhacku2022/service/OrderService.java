package cc.rits.openhacku2022.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.api.request.OrderCreateRequest;
import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.ForbiddenException;
import cc.rits.openhacku2022.exception.NotFoundException;
import cc.rits.openhacku2022.model.MenuModel;
import cc.rits.openhacku2022.model.OrderMenuModel;
import cc.rits.openhacku2022.model.OrderModel;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.repository.MenuRepository;
import cc.rits.openhacku2022.repository.OrderRepository;
import cc.rits.openhacku2022.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

/**
 * 注文サービス
 */
@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {

    private final ShopRepository shopRepository;

    private final OrderRepository orderRepository;

    private final MenuRepository menuRepository;

    /**
     * 注文作成
     * 
     * @param shopId 店舗ID
     * @param requestBody 注文作成リクエスト
     * @param transaction 取引
     */
    public void createOrder(final Integer shopId, final OrderCreateRequest requestBody, final TransactionModel transaction) {
        // 店舗の存在チェック
        this.shopRepository.selectById(shopId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHOP));

        // 取引中の店舗かチェック
        if (!Objects.equals(shopId, transaction.getShopId())) {
            throw new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION);
        }

        // メニューの存在チェック
        final var menuIds = this.menuRepository.selectByShopId(shopId).stream() //
            .map(MenuModel::getId) //
            .collect(Collectors.toList());
        final var specifiedMenuIds = requestBody.getMenus().stream() //
            .map(OrderCreateRequest.OrderMenuRequest::getMenuId) //
            .collect(Collectors.toList());
        if (!new HashSet<>(menuIds).containsAll(specifiedMenuIds)) {
            throw new NotFoundException(ErrorCode.NOT_FOUND_MENU);
        }

        // 注文を作成
        final var orderMenus = requestBody.getMenus().stream() //
            .map(orderMenuRequest -> OrderMenuModel.builder() //
                .menuId(orderMenuRequest.getMenuId()) //
                .quantity(orderMenuRequest.getQuantity()) //
                .build() //
            ).collect(Collectors.toList());
        final var order = OrderModel.builder() //
            .transactionId(transaction.getId()) //
            .menus(orderMenus) //
            .build();
        this.orderRepository.insert(order);
    }

}

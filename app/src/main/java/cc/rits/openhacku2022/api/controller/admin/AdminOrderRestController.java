package cc.rits.openhacku2022.api.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cc.rits.openhacku2022.api.request.OrderStatusUpdateRequest;
import cc.rits.openhacku2022.api.validation.RequestValidation;
import cc.rits.openhacku2022.model.ShopModel;
import cc.rits.openhacku2022.service.admin.AdminOrderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 注文コントローラ(管理者)
 */
@Tag(name = "Admin Order", description = "注文")
@RestController
@RequestMapping(path = "/api/admin/shops/{shop_id}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class AdminOrderRestController {

    private final AdminOrderService adminOrderService;

    /**
     * 注文ステータス更新API(管理者)
     *
     * @param shopId 店舗ID
     * @param orderId 注文ID
     * @param menuId メニューID
     * @param requestBody 注文ステータス更新リクエスト
     * @param shop 店舗
     */
    @PutMapping("/{order_id}/menus/{menu_id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrderStatus( //
        @PathVariable("shop_id") final Integer shopId, //
        @PathVariable("order_id") final Integer orderId, //
        @PathVariable("menu_id") final Integer menuId, //
        @RequestValidation @RequestBody final OrderStatusUpdateRequest requestBody, //
        @Parameter(hidden = true) final ShopModel shop //
    ) {
        this.adminOrderService.updateOrderStatus(shopId, orderId, menuId, requestBody, shop);
    }

}

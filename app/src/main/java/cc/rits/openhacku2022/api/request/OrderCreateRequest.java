package cc.rits.openhacku2022.api.request;

import java.util.List;

import cc.rits.openhacku2022.exception.BadRequestException;
import cc.rits.openhacku2022.exception.BaseException;
import cc.rits.openhacku2022.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文作成リクエスト
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateRequest implements BaseRequest {

    /**
     * メニューリスト
     */
    @Schema(required = true)
    List<OrderMenuRequest> menus;

    @Override
    public void validate() throws BaseException {
        if (this.getMenus().isEmpty()) {
            throw new BadRequestException(ErrorCode.ORDER_MENUS_MUST_NOT_BE_EMPTY);
        }

        this.getMenus().forEach(orderMenuRequest -> {
            if (orderMenuRequest.getQuantity() < 1) {
                throw new BadRequestException(ErrorCode.INVALID_ORDER_MENU_QUANTITY);
            }
        });
    }

    /**
     * 注文メニューリクエスト
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderMenuRequest {

        /**
         * メニューID
         */
        @Schema(required = true)
        Integer menuId;

        /**
         * 個数
         */
        @Schema(required = true)
        Integer quantity;

    }

}

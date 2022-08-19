package cc.rits.openhacku2022.api.response;

import cc.rits.openhacku2022.db.entity.Menu;
import cc.rits.openhacku2022.model.MenuModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * メニューレスポンス
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {

    /**
     * メニューID
     */
    @Schema(required = true)
    Integer id;

    /**
     * 店舗ID
     */
    @Schema(required = true)
    Integer shopId;

    /**
     * メニュー名
     */
    @Schema(required = true)
    String name;

    /**
     * 価格
     */
    @Schema(required = true)
    Integer price;

    /**
     * 画像
     */
    @Schema(required = true)
    String imageUrl;

    public MenuResponse(final MenuModel menu) {
        this.id = menu.getId();
        this.shopId = menu.getShopId();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.imageUrl = menu.getImageUrl();
    }

    public MenuResponse(final Menu menu) {
        this.id = menu.getId();
        this.shopId = menu.getShopId();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.imageUrl = menu.getImageUrl();
    }

}

package cc.rits.openhacku2022.api.response;

import cc.rits.openhacku2022.model.MenuModel;
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
    Integer id;

    /**
     * 店舗ID
     */
    Integer shopId;

    /**
     * メニュー名
     */
    String name;

    /**
     * 価格
     */
    Integer price;

    /**
     * 画像
     */
    String image;

    public MenuResponse(final MenuModel menuModel) {
        this.id = menuModel.getId();
        this.shopId = menuModel.getShopId();
        this.name = menuModel.getName();
        this.price = menuModel.getPrice();
        this.image = menuModel.getImage();
    }

}

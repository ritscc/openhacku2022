package cc.rits.openhacku2022.model;

import cc.rits.openhacku2022.db.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * メニューモデル
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuModel {

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
    String imageUrl;

    public MenuModel(final Menu menu) {
        this.id = menu.getId();
        this.shopId = menu.getShopId();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.imageUrl = menu.getImageUrl();
    }

}

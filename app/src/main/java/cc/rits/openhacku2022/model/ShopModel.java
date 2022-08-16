package cc.rits.openhacku2022.model;

import cc.rits.openhacku2022.db.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店舗モデル
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopModel {

    /**
     * 店舗ID
     */
    private Integer id;

    /**
     * 店舗名
     */
    private String name;

    /**
     * 店舗コード
     */
    private String code;

    /**
     * パスワード
     */
    private String password;

    public ShopModel(final Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
        this.code = shop.getCode();
        this.password = shop.getPassword();
    }

}

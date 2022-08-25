package cc.rits.openhacku2022.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import cc.rits.openhacku2022.db.entity.Shop;
import cc.rits.openhacku2022.db.entity.join.ShopWithTables;
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
public class ShopModel implements Serializable {

    /**
     * 店舗ID
     */
    Integer id;

    /**
     * 店舗名
     */
    String name;

    /**
     * 店舗コード
     */
    String code;

    /**
     * パスワード
     */
    String password;

    /**
     * テーブルリスト
     */
    List<TableModel> tables;

    public ShopModel(final Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
        this.code = shop.getCode();
        this.password = shop.getPassword();
    }

    public ShopModel(final ShopWithTables shop) {
        this.id = shop.getId();
        this.name = shop.getName();
        this.code = shop.getCode();
        this.password = shop.getPassword();
        this.tables = shop.getTables().stream() //
            .map(TableModel::new) //
            .collect(Collectors.toList());
    }

}

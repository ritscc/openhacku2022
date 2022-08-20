package cc.rits.openhacku2022.factory;

import org.springframework.stereotype.Component;

import cc.rits.openhacku2022.db.entity.Menu;
import cc.rits.openhacku2022.model.MenuModel;

/**
 * メニューファクトリ
 */
@Component
public class MenuFactory {

    /**
     * メニューを作成
     *
     * @param menuModel model
     * @return entity
     */
    public Menu createMenu(final MenuModel menuModel) {
        return Menu.builder() //
            .id(menuModel.getId()) //
            .shopId(menuModel.getShopId()) //
            .name(menuModel.getName()) //
            .price(menuModel.getPrice()) //
            .imageUrl(menuModel.getImageUrl()) //
            .build();
    }

}

package cc.rits.openhacku2022.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import cc.rits.openhacku2022.db.entity.MenuExample;
import cc.rits.openhacku2022.db.mapper.MenuMapper;
import cc.rits.openhacku2022.factory.MenuFactory;
import cc.rits.openhacku2022.model.MenuModel;
import lombok.RequiredArgsConstructor;

/**
 * メニューリポジトリ
 */
@Repository
@RequiredArgsConstructor
public class MenuRepository {

    private final MenuMapper menuMapper;

    private final MenuFactory menuFactory;

    /**
     * 店舗IDからメニューリストを取得
     * 
     * @param shopId 店舗ID
     * @return メニューリスト
     */
    public List<MenuModel> selectByShopId(final Integer shopId) {
        final var example = new MenuExample();
        example.createCriteria() //
            .andShopIdEqualTo(shopId);

        return this.menuMapper.selectByExample(example).stream() //
            .map(MenuModel::new) //
            .collect(Collectors.toList());
    }

    /**
     * メニューを取得
     * 
     * @param id メニューID
     * @return メニュー
     */
    public Optional<MenuModel> selectByIdAndShopId(final Integer id, final Integer shopId) {
        final var example = new MenuExample();
        example.createCriteria() //
            .andIdEqualTo(id) //
            .andShopIdEqualTo(shopId);
        return this.menuMapper.selectByExample(example).stream() //
            .map(MenuModel::new) //
            .findFirst();
    }

    /**
     * メニューを作成
     * 
     * @param menuModel メニュー
     */
    public void insert(final MenuModel menuModel) {
        final var menu = this.menuFactory.createMenu(menuModel);
        this.menuMapper.insertSelective(menu);
    }

    /**
     * メニューを削除
     * 
     * @param id メニューID
     */
    public void deleteById(final Integer id) {
        this.menuMapper.deleteByPrimaryKey(id);
    }

    /**
     * メニューを更新
     * 
     * @param menuModel メニュー
     */
    public void update(final MenuModel menuModel) {
        final var menu = this.menuFactory.createMenu(menuModel);
        this.menuMapper.updateByPrimaryKeySelective(menu);
    }

}

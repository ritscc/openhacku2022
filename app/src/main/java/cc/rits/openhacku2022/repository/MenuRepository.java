package cc.rits.openhacku2022.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import cc.rits.openhacku2022.db.entity.MenuExample;
import cc.rits.openhacku2022.db.mapper.MenuMapper;
import cc.rits.openhacku2022.model.MenuModel;
import lombok.RequiredArgsConstructor;

/**
 * メニューリポジトリ
 */
@Repository
@RequiredArgsConstructor
public class MenuRepository {

    private final MenuMapper menuMapper;

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
    public Optional<MenuModel> selectById(final Integer id) {
        final var example = new MenuExample();
        example.createCriteria().andIdEqualTo(id);

        return this.menuMapper.selectByExample(example).stream() //
            .map(MenuModel::new) //
            .findFirst();
    }

    /**
     * メニューを削除
     * 
     * @param id メニューID
     */
    public void deleteById(final Integer id) {
        this.menuMapper.deleteByPrimaryKey(id);
    }

}

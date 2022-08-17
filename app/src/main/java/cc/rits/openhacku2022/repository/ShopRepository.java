package cc.rits.openhacku2022.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import cc.rits.openhacku2022.db.entity.ShopExample;
import cc.rits.openhacku2022.db.mapper.ShopMapper;
import cc.rits.openhacku2022.model.ShopModel;
import lombok.RequiredArgsConstructor;

/**
 * 店舗リポジトリ
 */
@Repository
@RequiredArgsConstructor
public class ShopRepository {

    private final ShopMapper shopMapper;

    /**
     * IDから店舗を取得
     * 
     * @param id 店舗ID
     * @return 店舗
     */
    public Optional<ShopModel> selectById(final Integer id) {
        final var example = new ShopExample();
        example.createCriteria().andIdEqualTo(id);
        return this.shopMapper.selectByExample(example).stream() //
            .map(ShopModel::new) //
            .findFirst();
    }

    /**
     * 店舗コードから店舗を取得
     *
     * @param code 店舗コード
     * @return 店舗
     */
    public Optional<ShopModel> selectByCode(final String code) {
        final var example = new ShopExample();
        example.createCriteria().andCodeEqualTo(code);
        return this.shopMapper.selectByExample(example).stream() //
            .map(ShopModel::new) //
            .findFirst();
    }

}

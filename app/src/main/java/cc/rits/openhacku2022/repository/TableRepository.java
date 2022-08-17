package cc.rits.openhacku2022.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import cc.rits.openhacku2022.db.mapper.ShopTableMapper;
import cc.rits.openhacku2022.model.TableModel;
import lombok.RequiredArgsConstructor;

/**
 * テーブルリポジトリ
 */
@Repository
@RequiredArgsConstructor
public class TableRepository {

    private final ShopTableMapper shopTableMapper;

    /**
     * 店舗IDからテーブルリストを取得
     * 
     * @param shopId 店舗ID
     * @return テーブルリスト
     */
    public List<TableModel> selectByShopId(final Integer shopId) {
        return this.shopTableMapper.selectByShopId(shopId).stream() //
            .map(TableModel::new) //
            .collect(Collectors.toList());
    }

}

package cc.rits.openhacku2022.db.mapper;

import java.util.List;

import cc.rits.openhacku2022.db.entity.join.ShopTableWithTransaction;
import cc.rits.openhacku2022.db.mapper.base.ShopTableBaseMapper;

public interface ShopTableMapper extends ShopTableBaseMapper {

    List<ShopTableWithTransaction> selectByShopId(final Integer shopID);

}

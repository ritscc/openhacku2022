package cc.rits.openhacku2022.db.mapper;

import cc.rits.openhacku2022.db.entity.join.ShopWithTables;
import cc.rits.openhacku2022.db.mapper.base.ShopBaseMapper;

import java.util.Optional;

public interface ShopMapper extends ShopBaseMapper {

    Optional<ShopWithTables> selectByCode(final String code);

}

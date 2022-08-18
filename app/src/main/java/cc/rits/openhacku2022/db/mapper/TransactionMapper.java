package cc.rits.openhacku2022.db.mapper;

import cc.rits.openhacku2022.db.entity.join.TransactionWithShopNameAndOrdersAndTable;
import cc.rits.openhacku2022.db.mapper.base.TransactionBaseMapper;

public interface TransactionMapper extends TransactionBaseMapper {

    TransactionWithShopNameAndOrdersAndTable selectByIdAndShopId(final Integer id, final Integer shopId);

}

package cc.rits.openhacku2022.db.mapper;

import cc.rits.openhacku2022.db.entity.join.TransactionWithShopNameAndOrdersAndTable;
import cc.rits.openhacku2022.db.mapper.base.TransactionBaseMapper;

import java.util.List;

public interface TransactionMapper extends TransactionBaseMapper {

    List<TransactionWithShopNameAndOrdersAndTable> selectByIdAndShopId(final Integer id, final Integer shopId);

}

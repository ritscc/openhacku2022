package cc.rits.openhacku2022.db.mapper;

import java.util.Optional;

import cc.rits.openhacku2022.db.entity.join.OrderWithOrderMenus;
import cc.rits.openhacku2022.db.mapper.base.OrderBaseMapper;

public interface OrderMapper extends OrderBaseMapper {

    Optional<OrderWithOrderMenus> selectByOrderId(final Integer orderId);

}

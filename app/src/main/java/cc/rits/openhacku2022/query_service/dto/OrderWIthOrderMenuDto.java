package cc.rits.openhacku2022.query_service.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cc.rits.openhacku2022.db.entity.join.OrderWithOrderMenus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderWIthOrderMenuDto {

    /**
     * 注文ID
     */
    private Integer id;

    /**
     * 注文日
     */
    private Date orderedDate;

    /**
     * 注文メニューリスト
     */
    private List<OrderMenuDto> menus;

    public OrderWIthOrderMenuDto(final OrderWithOrderMenus order) {
        this.id = order.getId();
        this.orderedDate = order.getOrderedDate();
        this.menus = order.getOrderMenus().stream() //
            .map(OrderMenuDto::new) //
            .collect(Collectors.toList());
    }

}

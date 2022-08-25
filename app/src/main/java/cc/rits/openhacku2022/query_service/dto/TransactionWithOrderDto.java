package cc.rits.openhacku2022.query_service.dto;

import java.util.List;
import java.util.stream.Collectors;

import cc.rits.openhacku2022.db.entity.join.TransactionWithShopNameAndOrdersAndTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取引DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionWithOrderDto {

    /**
     * 取引ID
     */
    private Integer id;

    /**
     * 店舗ID
     */
    private Integer shopId;

    /**
     * テーブルID
     */
    private Integer tableId;

    /**
     * テーブル番号
     */
    private Integer tableNumber;

    /**
     * 店舗名
     */
    private String shopName;

    /**
     * 取引コード
     */
    private String code;

    /**
     * 利用人数
     */
    private Integer numberOfPeople;

    /**
     * 注文リスト
     */
    private List<OrderWIthOrderMenuDto> orders;

    public TransactionWithOrderDto(final TransactionWithShopNameAndOrdersAndTable transaction) {
        this.id = transaction.getId();
        this.shopId = transaction.getShopId();
        this.tableId = transaction.getTableId();
        this.tableNumber = transaction.getTable().getNumber();
        this.shopName = transaction.getShopName();
        this.code = transaction.getCode();
        this.numberOfPeople = transaction.getNumberOfPeople();
        this.orders = transaction.getOrders().stream() //
            .map(OrderWIthOrderMenuDto::new) //
            .collect(Collectors.toList());
    }

}

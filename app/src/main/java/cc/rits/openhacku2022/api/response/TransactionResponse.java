package cc.rits.openhacku2022.api.response;

import java.util.List;
import java.util.stream.Collectors;

import cc.rits.openhacku2022.db.entity.join.TransactionWithShopNameAndOrdersAndTable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取引レスポンス
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    /**
     * 取引ID
     */
    @Schema(required = true)
    Integer id;

    /**
     * 店舗ID
     */
    @Schema(required = true)
    Integer shopId;

    /**
     * テーブル
     */
    @Schema(required = true)
    TableResponse table;

    /**
     * 取引コード
     */
    @Schema(required = true)
    String code;

    /**
     * 利用人数
     */
    @Schema(required = true)
    Integer numberOfPeople;

    /**
     * 店舗名
     */
    @Schema(required = true)
    String shopName;

    /**
     * 注文リスト
     */
    @Schema(required = true)
    List<OrderResponse> orders;

    public TransactionResponse(final TransactionWithShopNameAndOrdersAndTable transaction) {
        this.id = transaction.getId();
        this.shopId = transaction.getShopId();
        this.table = new TableResponse(transaction.getTable());
        this.code = transaction.getCode();
        this.numberOfPeople = transaction.getNumberOfPeople();
        this.shopName = transaction.getShopName();
        this.orders = transaction.getOrders().stream() //
            .map(OrderResponse::new) //
            .collect(Collectors.toList());
    }

}

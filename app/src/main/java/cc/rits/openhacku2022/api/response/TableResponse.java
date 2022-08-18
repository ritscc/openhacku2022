package cc.rits.openhacku2022.api.response;

import cc.rits.openhacku2022.model.TableModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * テーブルレスポンス
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableResponse {

    /**
     * テーブルID
     */
    @Schema(required = true)
    Integer id;

    /**
     * 店舗ID
     */
    @Schema(required = true)
    Integer shopId;

    /**
     * 座れる人数
     */
    @Schema(required = true)
    Integer capacity;

    /**
     * 利用中フラグ
     */
    @Schema(required = true)
    Boolean isUsed;

    public TableResponse(final TableModel table) {
        this.id = table.getId();
        this.shopId = table.getShopId();
        this.capacity = table.getCapacity();
        this.isUsed = table.getIsUsed();
    }

}

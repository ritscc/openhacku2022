package cc.rits.openhacku2022.api.response;

import java.util.List;
import java.util.stream.Collectors;

import cc.rits.openhacku2022.model.ShopModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店舗レスポンス
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopResponse {

    /**
     * 店舗ID
     */
    @Schema(required = true)
    private Integer id;

    /**
     * 店舗名
     */
    @Schema(required = true)
    private String name;

    /**
     * 店舗コード
     */
    @Schema(required = true)
    private String code;

    /**
     * テーブルリスト
     */
    @Schema(required = true)
    private List<TableResponse> tables;

    public ShopResponse(final ShopModel shopModel) {
        this.id = shopModel.getId();
        this.name = shopModel.getName();
        this.code = shopModel.getCode();
        this.tables = shopModel.getTables().stream() //
            .map(TableResponse::new) //
            .collect(Collectors.toList());
    }

}

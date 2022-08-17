package cc.rits.openhacku2022.api.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * メニューレスポンス
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenusResponse {

    /**
     * メニューリスト
     */
    @Schema(required = true)
    List<MenuResponse> menus;

}

package cc.rits.openhacku2022.api.response;

import java.util.List;

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
    List<MenuResponse> menus;

}

package cc.rits.openhacku2022.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 決済レスポンス
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutResponse {

    /**
     * リダイレクトURL
     */
    @Schema(required = true)
    String redirectUrl;

}

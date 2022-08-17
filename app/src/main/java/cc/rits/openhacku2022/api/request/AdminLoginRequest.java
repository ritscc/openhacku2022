package cc.rits.openhacku2022.api.request;

import cc.rits.openhacku2022.exception.BaseException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ログインリクエスト(管理者用)
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginRequest implements BaseRequest {

    /**
     * 店舗コード
     */
    @Schema(required = true)
    String code;

    /**
     * パスワード
     */
    @Schema(required = true)
    String password;

    @Override
    public void validate() throws BaseException {}

}

package cc.rits.openhacku2022.api.request;

import cc.rits.openhacku2022.exception.BaseException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザログインリクエスト
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest implements BaseRequest {

    /**
     * メールアドレス
     */
    @Schema(required = true)
    String email;

    /**
     * パスワード
     */
    @Schema(required = true)
    String password;

    @Override
    public void validate() throws BaseException {}

}

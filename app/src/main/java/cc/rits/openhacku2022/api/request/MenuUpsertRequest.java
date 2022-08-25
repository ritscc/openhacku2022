package cc.rits.openhacku2022.api.request;

import cc.rits.openhacku2022.exception.BadRequestException;
import cc.rits.openhacku2022.exception.BaseException;
import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.util.ValidationUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * メニュー作成/更新リクエスト
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuUpsertRequest implements BaseRequest {

    /**
     * 名前
     */
    @Schema(required = true)
    String name;

    /**
     * 金額
     */
    @Schema(required = true)
    Integer price;

    /**
     * 画像(Base64)
     */
    @Schema(required = true)
    String image;

    @Override
    public void validate() throws BaseException {
        // 名前
        if (!ValidationUtil.checkStringLength(this.getName(), 1, 100)) {
            throw new BadRequestException(ErrorCode.INVALID_MENU_NAME);
        }

        // 金額
        if (this.getPrice() < 1) {
            throw new BadRequestException(ErrorCode.INVALID_MENU_PRICE);
        }
    }

}

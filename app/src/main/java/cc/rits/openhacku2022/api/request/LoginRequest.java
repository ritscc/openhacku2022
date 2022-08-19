package cc.rits.openhacku2022.api.request;

import cc.rits.openhacku2022.exception.BadRequestException;
import cc.rits.openhacku2022.exception.BaseException;
import cc.rits.openhacku2022.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ログインリクエスト
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements BaseRequest {

    /**
     * 店舗ID
     */
    @Schema(required = true)
    Integer shopId;

    /**
     * 利用人数
     */
    @Schema(required = true)
    Integer numberOfPeople;

    @Override
    public void validate() throws BaseException {
        // 利用人数
        if (this.getNumberOfPeople() < 1) {
            throw new BadRequestException(ErrorCode.INVALID_NUMBER_OF_PEOPLE);
        }
    }

}

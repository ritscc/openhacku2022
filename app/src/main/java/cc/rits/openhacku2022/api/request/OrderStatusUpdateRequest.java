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
 * 注文作成リクエスト
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpdateRequest implements BaseRequest {

    /**
     * 注文ステータス
     */
    @Schema(required = true)
    Integer status;

    @Override
    public void validate() throws BaseException {
        if (this.status < 0) {
            throw new BadRequestException(ErrorCode.INVALID_ORDER_STATUS);
        }
    }

}

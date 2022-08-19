package cc.rits.openhacku2022.enums;

import java.util.Arrays;

import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.InternalServerErrorException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 注文ステータスEnum
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    /**
     * 注文受け付け
     */
    ACCEPTED(0),

    /**
     * 準備中
     */
    IN_PREPARATION(1),

    /**
     * 提供済み
     */
    COMPLETED(2);

    /**
     * 注文ステータスID
     */
    private final Integer id;

    /**
     * 注文ステータスを検索
     * 
     * @param id 注文ステータスID
     * @return 注文ステータス
     */
    public static OrderStatusEnum find(final Integer id) {
        return Arrays.stream(values()).filter(e -> e.getId().equals(id)) //
            .findFirst().orElseThrow(() -> new InternalServerErrorException(ErrorCode.UNEXPECTED_ERROR));
    }

}

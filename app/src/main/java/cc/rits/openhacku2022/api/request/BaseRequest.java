package cc.rits.openhacku2022.api.request;

import cc.rits.openhacku2022.exception.BaseException;

/**
 * リクエストボディのインターフェース
 */
public interface BaseRequest {

    /**
     * リクエストボディのバリデーション
     */
    void validate() throws BaseException;

}

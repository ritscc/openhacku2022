package cc.rits.openhacku2022.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

/**
 * 409 Conflict
 */
public class ConflictException extends BaseException {
    /**
     * create conflict exception
     *
     * @param errorCode error code
     */
    public ConflictException(final ErrorCode errorCode) {
        super(CONFLICT, errorCode);
    }

}

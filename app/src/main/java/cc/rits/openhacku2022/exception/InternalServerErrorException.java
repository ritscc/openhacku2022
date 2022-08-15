package cc.rits.openhacku2022.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 500 Internal Server Error
 */
public class InternalServerErrorException extends BaseException {

    /**
     * create conflict exception
     *
     * @param errorCode error code
     */
    public InternalServerErrorException(final ErrorCode errorCode) {
        super(INTERNAL_SERVER_ERROR, errorCode);
    }

}

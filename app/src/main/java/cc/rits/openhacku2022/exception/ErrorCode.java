package cc.rits.openhacku2022.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * エラーコード
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * 400 Bad Request
     */
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "exception.bad_request.validation_error"),

    INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_request_parameter"),

    /**
     * 401 Unauthorized
     */
    USER_NOT_LOGGED_IN(HttpStatus.UNAUTHORIZED, "exception.unauthorized.user_not_logged_in"),

    INCORRECT_EMAIL_OR_PASSWORD(HttpStatus.UNAUTHORIZED, "exception.unauthorized.incorrect_email_or_password"),

    /**
     * 404 Not Found
     */
    NOT_FOUND_API(HttpStatus.NOT_FOUND, "exception.not_found.api"),

    /**
     * 500 Internal Server Error
     */
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "exception.internal_server_error.unexpected_error"),

    FAILED_TO_UPLOAD_FILE(HttpStatus.INTERNAL_SERVER_ERROR, "exception.internal_server_error.failed_to_upload_file");

    /**
     * HTTPステータスコード
     */
    private final HttpStatus status;

    /**
     * resources/i18n/messages.ymlのキーに対応
     */
    private final String messageKey;

}

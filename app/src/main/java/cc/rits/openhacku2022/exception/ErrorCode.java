package cc.rits.openhacku2022.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

    INVALID_PASSWORD_LENGTH(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_password_length"),

    PASSWORD_IS_TOO_SIMPLE(HttpStatus.BAD_REQUEST, "exception.bad_request.password_is_too_simple"),

    INVALID_NUMBER_OF_PEOPLE(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_number_of_people"),

    ALL_TABLES_ARE_BOOKED(HttpStatus.BAD_REQUEST, "exception.bad_request.all_tables_are_booked"),

    ORDER_MENUS_MUST_NOT_BE_EMPTY(HttpStatus.BAD_REQUEST, "exception.bad_request.order_menus_must_not_be_empty"),

    INVALID_ORDER_MENU_QUANTITY(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_order_menu_quantity"),

    /**
     * 401 Unauthorized
     */
    USER_NOT_LOGGED_IN(HttpStatus.UNAUTHORIZED, "exception.unauthorized.user_not_logged_in"),

    INCORRECT_CODE_OR_PASSWORD(HttpStatus.UNAUTHORIZED, "exception.unauthorized.incorrect_code_or_password"),

    /**
     * 403 Forbidden
     */
    USER_HAS_NO_PERMISSION(HttpStatus.FORBIDDEN, "exception.forbidden.user_has_no_permission"),

    /**
     * 404 Not Found
     */
    NOT_FOUND_API(HttpStatus.NOT_FOUND, "exception.not_found.api"),

    NOT_FOUND_SHOP(HttpStatus.NOT_FOUND, "exception.not_found.shop"),

    NOT_FOUND_MENU(HttpStatus.NOT_FOUND, "exception.not_found.menu"),

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

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

    INVALID_USER_EMAIL(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_user_email"),

    INVALID_RACK_NAME(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_rack_name"),

    RACK_CANNOT_BE_DELETED(HttpStatus.BAD_REQUEST, "exception.bad_request.rack_cannot_be_deleted"),

    INVALID_USER_FIRST_NAME(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_user_first_name"),

    INVALID_USER_LAST_NAME(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_user_last_name"),

    SHOP_USER_GROUPS_MUST_NOT_BE_EMPTY(HttpStatus.BAD_REQUEST, "exception.bad_request.shop_user_groups_must_not_be_empty"),

    SHOP_ROLES_MUST_NOT_BE_EMPTY(HttpStatus.BAD_REQUEST, "exception.bad_request.shop_roles_must_not_be_empty"),

    INVALID_SHOP_USER_GROUP_NAME(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_shop_user_group_name"),

    INVALID_SHOP_USER_GROUP_DESCRIPTION(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_shop_user_group_description"),

    INVALID_SHOP_USER_GROUP_ROLES(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_shop_user_group_roles"),

    INVALID_VEHICLE_NAME(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_vehicle_name"),

    INVALID_VEHICLE_CAPACITY(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_vehicle_capacity"),

    SHOP_USER_GROUP_CANNOT_BE_DELETED(HttpStatus.BAD_REQUEST, "exception.bad_request.shop_user_group_cannot_be_deleted"),

    INVALID_PRODUCT_NAME(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_product_name"),

    INVALID_PRODUCT_DESCRIPTION(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_product_description"),

    INVALID_PRODUCT_PRICE(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_product_price"),

    INVALID_PRODUCT_TAX_RATE(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_product_tax_rate"),

    INVALID_PRODUCT_JAN_CODE(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_product_jan_code"),

    INVALID_OLD_PASSWORD(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_old_password"),

    INVALID_PRODUCT_TEMPERATURE(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_product_temperature"),

    INVALID_PRODUCT_SALABLE_DAYS(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_product_salable_days"),

    INVALID_SHOP_CODE(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_shop_code"),

    INVALID_SHOP_NAME(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_shop_name"),

    INVALID_SHOP_TEL(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_shop_tel"),

    INVALID_SHOP_ZIPCODE(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_shop_zipcode"),

    INVALID_SHOP_LOCALITY(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_shop_locality"),

    INVALID_SHOP_STREET(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_shop_street"),

    INVALID_SHOP_BUILDING(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_shop_building"),

    INVALID_SHOP_STATUS(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_shop_status"),

    INVALID_PREFECTURE_CODE(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_prefecture_code"),

    INVALID_PRODUCT_STOCK_QUANTITY(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_product_stock_quantity"),

    INVALID_PRODUCT_STOCK_EXPIRATION_DATE(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_product_stock_expiration_date"),

    INVALID_PRODUCT_STOCK_STATUS(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_product_stock_status"),

    PICKING_IS_ALREADY_STARTED(HttpStatus.BAD_REQUEST, "exception.bad_request.picking_is_already_started"),

    PICKING_IS_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "exception.bad_request.picking_is_already_completed"),

    PICKING_SCHEDULED_DATE_IS_NOT_TODAY(HttpStatus.BAD_REQUEST, "exception.bad_request.picking_scheduled_date_is_not_today"),

    PICKING_IS_NOT_STARTED(HttpStatus.BAD_REQUEST, "exception.bad_request.picking_is_not_started"),

    DELIVERY_IS_ALREADY_STARTED(HttpStatus.BAD_REQUEST, "exception.bad_request.delivery_is_already_started"),

    DELIVERY_IS_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "exception.bad_request.delivery_is_already_completed"),

    DELIVERY_SCHEDULED_DATE_IS_NOT_TODAY(HttpStatus.BAD_REQUEST, "exception.bad_request.delivery_scheduled_date_is_not_today"),

    INVALID_PRODUCT_LABEL_NAME(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_product_label_name"),

    INVALID_NUMBER_OF_PRODUCT_IMAGES(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_number_of_product_images"),

    DELIVERY_IS_NOT_STARTED(HttpStatus.BAD_REQUEST, "exception.bad_request.delivery_is_not_started"),

    DELIVERY_ROUTES_ARE_NOT_COMPLETED(HttpStatus.BAD_REQUEST, "exception.bad_request.delivery_routes_are_not_completed"),

    INVALID_COUPON_NAME(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_coupon_name"),

    INVALID_COUPON_CODE(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_coupon_code"),

    INVALID_COUPON_TYPE(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_coupon_type"),

    INVALID_COUPON_VALUE(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_coupon_value"),

    INVALID_COUPON_EXPIRATION_DATE(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_coupon_expiration_date"),

    INVALID_COUPON_NUMBER_LIMIT(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_coupon_number_limit"),

    INVALID_COUPON_MIN_PURCHASE_AMOUNT(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_coupon_min_purchase_amount"),

    PRODUCT_NEEDS_EXPIRATION_DATE(HttpStatus.BAD_REQUEST, "exception.bad_request.product_needs_expiration_date"),

    INVALID_SALABLE_OFFSET(HttpStatus.BAD_REQUEST, "exception.bad_request.invalid_salable_offset"),

    /**
     * 401 Unauthorized
     */
    USER_NOT_LOGGED_IN(HttpStatus.UNAUTHORIZED, "exception.unauthorized.user_not_logged_in"),

    INCORRECT_EMAIL_OR_PASSWORD(HttpStatus.UNAUTHORIZED, "exception.unauthorized.incorrect_email_or_password"),

    /**
     * 403 Forbidden
     */
    USER_HAS_NO_PERMISSION(HttpStatus.FORBIDDEN, "exception.forbidden.user_has_no_permission"),

    USER_IS_NOT_ASSIGNED_PICKING(HttpStatus.FORBIDDEN, "exception.forbidden.user_is_not_assigned_picking"),

    USER_IS_NOT_ASSIGNED_DELIVERY(HttpStatus.FORBIDDEN, "exception.forbidden.user_is_not_assigned_delivery"),

    /**
     * 404 Not Found
     */
    NOT_FOUND_API(HttpStatus.NOT_FOUND, "exception.not_found.api"),

    NOT_FOUND_SHOP(HttpStatus.NOT_FOUND, "exception.not_found.shop"),

    NOT_FOUND_SHOP_RACK(HttpStatus.NOT_FOUND, "exception.not_found.shop_rack"),

    NOT_FOUND_SHOP_USER_GROUP(HttpStatus.NOT_FOUND, "exception.not_found.shop_user_group"),

    NOT_FOUND_VEHICLE(HttpStatus.NOT_FOUND, "exception.not_found.vehicle"),

    NOT_FOUND_SHOP_USER(HttpStatus.NOT_FOUND, "exception.not_found.shop_user"),

    NOT_FOUND_PRODUCT_LABEL(HttpStatus.NOT_FOUND, "exception.not_found.product_label"),

    NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, "exception.not_found.product"),

    NOT_FOUND_PRODUCT_STOCK(HttpStatus.NOT_FOUND, "exception.not_found.product_stock"),

    NOT_FOUND_PICKING(HttpStatus.NOT_FOUND, "exception.not_found.picking"),

    NOT_FOUND_DELIVERY(HttpStatus.NOT_FOUND, "exception.not_found.delivery"),

    NOT_FOUND_DELIVERY_ROUTE(HttpStatus.NOT_FOUND, "exception.not_found.delivery_route"),

    NOT_FOUND_COUPON(HttpStatus.NOT_FOUND, "exception.not_found.coupon"),

    /**
     * 409 Conflict
     */
    EMAIL_IS_ALREADY_USED(HttpStatus.CONFLICT, "exception.conflict.email_is_already_used"),

    SHOP_CODE_IS_ALREADY_USED(HttpStatus.CONFLICT, "exception.conflict.shop_code_is_already_used"),

    PRODUCT_NAME_IS_ALREADY_USED(HttpStatus.CONFLICT, "exception.conflict.product_name_is_already_used"),

    SHOP_USER_GROUP_NAME_IS_ALREADY_USED(HttpStatus.CONFLICT, "exception.conflict.shop_user_group_name_is_already_used"),

    RACK_NAME_IS_ALREADY_USED(HttpStatus.CONFLICT, "exception.conflict.rack_name_is_already_used"),

    VEHICLE_NAME_IS_ALREADY_USED(HttpStatus.CONFLICT, "exception.conflict.vehicle_name_is_already_used"),

    COUPON_NAME_IS_ALREADY_USED(HttpStatus.CONFLICT, "exception.conflict.coupon_name_is_already_used"),

    COUPON_CODE_IS_ALREADY_USED(HttpStatus.CONFLICT, "exception.conflict.coupon_code_is_already_used"),

    /**
     * 500 Internal Server Error
     */
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "exception.internal_server_error.unexpected_error"),

    FAILED_TO_UPLOAD_FILE(HttpStatus.INTERNAL_SERVER_ERROR, "exception.internal_server_error.failed_to_upload_file"),

    FAILED_TO_DELETE_FILE(HttpStatus.INTERNAL_SERVER_ERROR, "exception.internal_server_error.failed_to_delete_file"),

    FAILED_TO_SEND_MAIL(HttpStatus.INTERNAL_SERVER_ERROR, "exception.internal_server_error.failed_to_send_mail");

    /**
     * HTTPステータスコード
     */
    private final HttpStatus status;

    /**
     * resources/i18n/messages.ymlのキーに対応
     */
    private final String messageKey;

}

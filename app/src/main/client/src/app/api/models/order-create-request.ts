/* tslint:disable */
/* eslint-disable */
import { OrderMenuRequest } from "./order-menu-request";

/**
 * 注文作成リクエスト
 */
export interface OrderCreateRequest {
    /**
     * メニューリスト
     */
    menus: Array<OrderMenuRequest>;
}

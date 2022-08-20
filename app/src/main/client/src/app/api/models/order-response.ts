/* tslint:disable */
/* eslint-disable */
import { OrderMenuResponse } from "./order-menu-response";

/**
 * 注文レスポンス
 */
export interface OrderResponse {
    /**
     * 注文ID
     */
    id: number;

    /**
     * 注文メニューリスト
     */
    menus: Array<OrderMenuResponse>;

    /**
     * 注文日
     */
    orderedDate: string;
}

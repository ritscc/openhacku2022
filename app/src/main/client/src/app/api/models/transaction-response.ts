/* tslint:disable */
/* eslint-disable */
import { OrderResponse } from "./order-response";

/**
 * 取引レスポンス
 */
export interface TransactionResponse {
    /**
     * 取引コード
     */
    code: string;

    /**
     * 取引ID
     */
    id: number;

    /**
     * 利用人数
     */
    numberOfPeople: number;

    /**
     * 注文リスト
     */
    orders: Array<OrderResponse>;

    /**
     * 店舗ID
     */
    shopId: number;

    /**
     * 店舗名
     */
    shopName: string;

    /**
     * テーブルID
     */
    tableId: number;
}

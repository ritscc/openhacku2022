/* tslint:disable */
/* eslint-disable */
import { TableResponse } from "./table-response";

/**
 * 店舗レスポンス
 */
export interface ShopResponse {
    /**
     * 店舗コード
     */
    code: string;

    /**
     * 店舗ID
     */
    id: number;

    /**
     * 店舗名
     */
    name: string;

    /**
     * テーブルリスト
     */
    tables: Array<TableResponse>;
}

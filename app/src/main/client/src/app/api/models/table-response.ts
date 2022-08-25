/* tslint:disable */
/* eslint-disable */

/**
 * テーブルレスポンス
 */
export interface TableResponse {
    /**
     * 座れる人数
     */
    capacity: number;

    /**
     * テーブルID
     */
    id: number;

    /**
     * 利用中フラグ
     */
    isUsed: boolean;

    /**
     * テーブル番号
     */
    tableNumber: number;
}

/* tslint:disable */
/* eslint-disable */

/**
 * 注文メニューレスポンス
 */
export interface OrderMenuResponse {
    /**
     * メニューID
     */
    id: number;

    /**
     * 画像URL
     */
    imageUrl: string;

    /**
     * メニュー名
     */
    name: string;

    /**
     * 価格
     */
    price: number;

    /**
     * 注文数
     */
    quantity: number;

    /**
     * ステータス
     */
    status: number;
}

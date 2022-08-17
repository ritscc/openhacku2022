/* tslint:disable */
/* eslint-disable */

/**
 * メニューレスポンス
 */
export interface MenuResponse {
    /**
     * メニューID
     */
    id: number;

    /**
     * 画像
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
     * 店舗ID
     */
    shopId: number;
}

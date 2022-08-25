/* tslint:disable */
/* eslint-disable */

/**
 * メニュー作成/更新リクエスト
 */
export interface MenuUpsertRequest {
    /**
     * 画像(Base64)
     */
    image: string;

    /**
     * 名前
     */
    name: string;

    /**
     * 金額
     */
    price: number;
}

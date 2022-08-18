/* tslint:disable */
/* eslint-disable */

/**
 * ログインリクエスト(管理者用)
 */
export interface AdminLoginRequest {
    /**
     * 店舗コード
     */
    code: string;

    /**
     * パスワード
     */
    password: string;
}

/* tslint:disable */
/* eslint-disable */
import { MenuResponse } from "./menu-response";

/**
 * メニューレスポンス
 */
export interface MenusResponse {
    /**
     * メニューリスト
     */
    menus: Array<MenuResponse>;
}

/* tslint:disable */
/* eslint-disable */
import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { BaseService } from "../base-service";
import { ApiConfiguration } from "../api-configuration";
import { StrictHttpResponse } from "../strict-http-response";
import { RequestBuilder } from "../request-builder";
import { Observable } from "rxjs";
import { map, filter } from "rxjs/operators";

import { MenuUpsertRequest } from "../models/menu-upsert-request";
import { MenusResponse } from "../models/menus-response";

/**
 * メニュー
 */
@Injectable({
    providedIn: "root",
})
export class AdminMenuService extends BaseService {
    constructor(config: ApiConfiguration, http: HttpClient) {
        super(config, http);
    }

    /**
     * Path part for operation updateMenu
     */
    static readonly UpdateMenuPath = "/api/admin/shops/{shop_id}/menus/{menu_id}";

    /**
     * メニュー更新API.
     *
     * メニュー更新API
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `updateMenu()` instead.
     *
     * This method sends `application/json` and handles request body of type `application/json`.
     */
    updateMenu$Response(params: {
        /**
         * 店舗ID
         */
        shop_id: number;

        /**
         * メニューID
         */
        menu_id: number;

        /**
         * メニュー更新リクエスト
         */
        body: MenuUpsertRequest;
    }): Observable<StrictHttpResponse<void>> {
        const rb = new RequestBuilder(this.rootUrl, AdminMenuService.UpdateMenuPath, "put");
        if (params) {
            rb.path("shop_id", params.shop_id, {});
            rb.path("menu_id", params.menu_id, {});
            rb.body(params.body, "application/json");
        }

        return this.http
            .request(
                rb.build({
                    responseType: "text",
                    accept: "*/*",
                })
            )
            .pipe(
                filter((r: any) => r instanceof HttpResponse),
                map((r: HttpResponse<any>) => {
                    return (r as HttpResponse<any>).clone({
                        body: undefined,
                    }) as StrictHttpResponse<void>;
                })
            );
    }

    /**
     * メニュー更新API.
     *
     * メニュー更新API
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `updateMenu$Response()` instead.
     *
     * This method sends `application/json` and handles request body of type `application/json`.
     */
    updateMenu(params: {
        /**
         * 店舗ID
         */
        shop_id: number;

        /**
         * メニューID
         */
        menu_id: number;

        /**
         * メニュー更新リクエスト
         */
        body: MenuUpsertRequest;
    }): Observable<void> {
        return this.updateMenu$Response(params).pipe(
            map((r: StrictHttpResponse<void>) => r.body as void)
        );
    }

    /**
     * Path part for operation deleteMenu
     */
    static readonly DeleteMenuPath = "/api/admin/shops/{shop_id}/menus/{menu_id}";

    /**
     * メニュー削除API.
     *
     * メニュー削除API
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `deleteMenu()` instead.
     *
     * This method doesn't expect any request body.
     */
    deleteMenu$Response(params: {
        /**
         * 店舗ID
         */
        shop_id: number;

        /**
         * メニューID
         */
        menu_id: number;
    }): Observable<StrictHttpResponse<void>> {
        const rb = new RequestBuilder(this.rootUrl, AdminMenuService.DeleteMenuPath, "delete");
        if (params) {
            rb.path("shop_id", params.shop_id, {});
            rb.path("menu_id", params.menu_id, {});
        }

        return this.http
            .request(
                rb.build({
                    responseType: "text",
                    accept: "*/*",
                })
            )
            .pipe(
                filter((r: any) => r instanceof HttpResponse),
                map((r: HttpResponse<any>) => {
                    return (r as HttpResponse<any>).clone({
                        body: undefined,
                    }) as StrictHttpResponse<void>;
                })
            );
    }

    /**
     * メニュー削除API.
     *
     * メニュー削除API
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `deleteMenu$Response()` instead.
     *
     * This method doesn't expect any request body.
     */
    deleteMenu(params: {
        /**
         * 店舗ID
         */
        shop_id: number;

        /**
         * メニューID
         */
        menu_id: number;
    }): Observable<void> {
        return this.deleteMenu$Response(params).pipe(
            map((r: StrictHttpResponse<void>) => r.body as void)
        );
    }

    /**
     * Path part for operation getMenus1
     */
    static readonly GetMenus1Path = "/api/admin/shops/{shop_id}/menus";

    /**
     * メニューリスト取得API.
     *
     * メニューリスト取得API
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `getMenus1()` instead.
     *
     * This method doesn't expect any request body.
     */
    getMenus1$Response(params: {
        /**
         * 店舗ID
         */
        shop_id: number;
    }): Observable<StrictHttpResponse<MenusResponse>> {
        const rb = new RequestBuilder(this.rootUrl, AdminMenuService.GetMenus1Path, "get");
        if (params) {
            rb.path("shop_id", params.shop_id, {});
        }

        return this.http
            .request(
                rb.build({
                    responseType: "json",
                    accept: "application/json",
                })
            )
            .pipe(
                filter((r: any) => r instanceof HttpResponse),
                map((r: HttpResponse<any>) => {
                    return r as StrictHttpResponse<MenusResponse>;
                })
            );
    }

    /**
     * メニューリスト取得API.
     *
     * メニューリスト取得API
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `getMenus1$Response()` instead.
     *
     * This method doesn't expect any request body.
     */
    getMenus1(params: {
        /**
         * 店舗ID
         */
        shop_id: number;
    }): Observable<MenusResponse> {
        return this.getMenus1$Response(params).pipe(
            map((r: StrictHttpResponse<MenusResponse>) => r.body as MenusResponse)
        );
    }

    /**
     * Path part for operation createMenu
     */
    static readonly CreateMenuPath = "/api/admin/shops/{shop_id}/menus";

    /**
     * メニュー作成API.
     *
     * メニュー作成API
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `createMenu()` instead.
     *
     * This method sends `application/json` and handles request body of type `application/json`.
     */
    createMenu$Response(params: {
        /**
         * 店舗ID
         */
        shop_id: number;

        /**
         * メニュー作成リクエスト
         */
        body: MenuUpsertRequest;
    }): Observable<StrictHttpResponse<void>> {
        const rb = new RequestBuilder(this.rootUrl, AdminMenuService.CreateMenuPath, "post");
        if (params) {
            rb.path("shop_id", params.shop_id, {});
            rb.body(params.body, "application/json");
        }

        return this.http
            .request(
                rb.build({
                    responseType: "text",
                    accept: "*/*",
                })
            )
            .pipe(
                filter((r: any) => r instanceof HttpResponse),
                map((r: HttpResponse<any>) => {
                    return (r as HttpResponse<any>).clone({
                        body: undefined,
                    }) as StrictHttpResponse<void>;
                })
            );
    }

    /**
     * メニュー作成API.
     *
     * メニュー作成API
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `createMenu$Response()` instead.
     *
     * This method sends `application/json` and handles request body of type `application/json`.
     */
    createMenu(params: {
        /**
         * 店舗ID
         */
        shop_id: number;

        /**
         * メニュー作成リクエスト
         */
        body: MenuUpsertRequest;
    }): Observable<void> {
        return this.createMenu$Response(params).pipe(
            map((r: StrictHttpResponse<void>) => r.body as void)
        );
    }
}

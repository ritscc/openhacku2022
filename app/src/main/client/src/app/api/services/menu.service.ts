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

import { MenusResponse } from "../models/menus-response";

/**
 * メニュー
 */
@Injectable({
    providedIn: "root",
})
export class MenuService extends BaseService {
    constructor(config: ApiConfiguration, http: HttpClient) {
        super(config, http);
    }

    /**
     * Path part for operation getMenus
     */
    static readonly GetMenusPath = "/api/shops/{shop_id}/menus";

    /**
     * メニューリスト取得API.
     *
     * メニューリスト取得API
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `getMenus()` instead.
     *
     * This method doesn't expect any request body.
     */
    getMenus$Response(params: {
        /**
         * 店舗ID
         */
        shop_id: number;
    }): Observable<StrictHttpResponse<MenusResponse>> {
        const rb = new RequestBuilder(this.rootUrl, MenuService.GetMenusPath, "get");
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
     * To access the full response (for headers, for example), `getMenus$Response()` instead.
     *
     * This method doesn't expect any request body.
     */
    getMenus(params: {
        /**
         * 店舗ID
         */
        shop_id: number;
    }): Observable<MenusResponse> {
        return this.getMenus$Response(params).pipe(
            map((r: StrictHttpResponse<MenusResponse>) => r.body as MenusResponse)
        );
    }
}

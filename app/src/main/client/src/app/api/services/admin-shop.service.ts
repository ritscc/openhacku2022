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

import { ShopResponse } from "../models/shop-response";

/**
 * 店舗
 */
@Injectable({
    providedIn: "root",
})
export class AdminShopService extends BaseService {
    constructor(config: ApiConfiguration, http: HttpClient) {
        super(config, http);
    }

    /**
     * Path part for operation getShop
     */
    static readonly GetShopPath = "/api/admin/shops/me";

    /**
     * 店舗取得API(管理者).
     *
     * 店舗取得API(管理者)
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `getShop()` instead.
     *
     * This method doesn't expect any request body.
     */
    getShop$Response(params?: {}): Observable<StrictHttpResponse<ShopResponse>> {
        const rb = new RequestBuilder(this.rootUrl, AdminShopService.GetShopPath, "get");
        if (params) {
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
                    return r as StrictHttpResponse<ShopResponse>;
                })
            );
    }

    /**
     * 店舗取得API(管理者).
     *
     * 店舗取得API(管理者)
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `getShop$Response()` instead.
     *
     * This method doesn't expect any request body.
     */
    getShop(params?: {}): Observable<ShopResponse> {
        return this.getShop$Response(params).pipe(
            map((r: StrictHttpResponse<ShopResponse>) => r.body as ShopResponse)
        );
    }
}

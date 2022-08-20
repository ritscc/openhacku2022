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

import { OrderCreateRequest } from "../models/order-create-request";

/**
 * 注文
 */
@Injectable({
    providedIn: "root",
})
export class OrderService extends BaseService {
    constructor(config: ApiConfiguration, http: HttpClient) {
        super(config, http);
    }

    /**
     * Path part for operation createOrder
     */
    static readonly CreateOrderPath = "/api/shops/{shop_id}/orders";

    /**
     * 注文作成API.
     *
     * 注文作成API
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `createOrder()` instead.
     *
     * This method sends `application/json` and handles request body of type `application/json`.
     */
    createOrder$Response(params: {
        /**
         * 店舗ID
         */
        shop_id: number;

        /**
         * 注文作成リクエスト
         */
        body: OrderCreateRequest;
    }): Observable<StrictHttpResponse<void>> {
        const rb = new RequestBuilder(this.rootUrl, OrderService.CreateOrderPath, "post");
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
     * 注文作成API.
     *
     * 注文作成API
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `createOrder$Response()` instead.
     *
     * This method sends `application/json` and handles request body of type `application/json`.
     */
    createOrder(params: {
        /**
         * 店舗ID
         */
        shop_id: number;

        /**
         * 注文作成リクエスト
         */
        body: OrderCreateRequest;
    }): Observable<void> {
        return this.createOrder$Response(params).pipe(
            map((r: StrictHttpResponse<void>) => r.body as void)
        );
    }
}

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

import { OrderStatusUpdateRequest } from "../models/order-status-update-request";

/**
 * 注文
 */
@Injectable({
    providedIn: "root",
})
export class AdminOrderService extends BaseService {
    constructor(config: ApiConfiguration, http: HttpClient) {
        super(config, http);
    }

    /**
     * Path part for operation updateOrderStatus
     */
    static readonly UpdateOrderStatusPath =
        "/api/admin/shops/{shop_id}/orders/{order_id}/menus/{menu_id}";

    /**
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `updateOrderStatus()` instead.
     *
     * This method sends `application/json` and handles request body of type `application/json`.
     */
    updateOrderStatus$Response(params: {
        shop_id: number;
        order_id: number;
        menu_id: number;
        body: OrderStatusUpdateRequest;
    }): Observable<StrictHttpResponse<void>> {
        const rb = new RequestBuilder(this.rootUrl, AdminOrderService.UpdateOrderStatusPath, "put");
        if (params) {
            rb.path("shop_id", params.shop_id, {});
            rb.path("order_id", params.order_id, {});
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
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `updateOrderStatus$Response()` instead.
     *
     * This method sends `application/json` and handles request body of type `application/json`.
     */
    updateOrderStatus(params: {
        shop_id: number;
        order_id: number;
        menu_id: number;
        body: OrderStatusUpdateRequest;
    }): Observable<void> {
        return this.updateOrderStatus$Response(params).pipe(
            map((r: StrictHttpResponse<void>) => r.body as void)
        );
    }
}

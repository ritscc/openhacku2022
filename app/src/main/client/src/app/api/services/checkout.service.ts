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

import { CheckoutResponse } from "../models/checkout-response";

/**
 * 支払い
 */
@Injectable({
    providedIn: "root",
})
export class CheckoutService extends BaseService {
    constructor(config: ApiConfiguration, http: HttpClient) {
        super(config, http);
    }

    /**
     * Path part for operation checkout
     */
    static readonly CheckoutPath = "/api/checkout";

    /**
     * 決済API.
     *
     * 決済API
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `checkout()` instead.
     *
     * This method doesn't expect any request body.
     */
    checkout$Response(params?: {}): Observable<StrictHttpResponse<CheckoutResponse>> {
        const rb = new RequestBuilder(this.rootUrl, CheckoutService.CheckoutPath, "get");
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
                    return r as StrictHttpResponse<CheckoutResponse>;
                })
            );
    }

    /**
     * 決済API.
     *
     * 決済API
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `checkout$Response()` instead.
     *
     * This method doesn't expect any request body.
     */
    checkout(params?: {}): Observable<CheckoutResponse> {
        return this.checkout$Response(params).pipe(
            map((r: StrictHttpResponse<CheckoutResponse>) => r.body as CheckoutResponse)
        );
    }
}

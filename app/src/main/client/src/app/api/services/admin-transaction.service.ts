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

import { TransactionResponse } from "../models/transaction-response";

/**
 * 取引
 */
@Injectable({
    providedIn: "root",
})
export class AdminTransactionService extends BaseService {
    constructor(config: ApiConfiguration, http: HttpClient) {
        super(config, http);
    }

    /**
     * Path part for operation getTransaction
     */
    static readonly GetTransactionPath = "/api/admin/shops/{shop_id}/transactions/{transaction_id}";

    /**
     * 取引取得API.
     *
     * 取引取得API
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `getTransaction()` instead.
     *
     * This method doesn't expect any request body.
     */
    getTransaction$Response(params: {
        /**
         * 店舗ID
         */
        shop_id: number;

        /**
         * 取引ID
         */
        transaction_id: number;
    }): Observable<StrictHttpResponse<TransactionResponse>> {
        const rb = new RequestBuilder(
            this.rootUrl,
            AdminTransactionService.GetTransactionPath,
            "get"
        );
        if (params) {
            rb.path("shop_id", params.shop_id, {});
            rb.path("transaction_id", params.transaction_id, {});
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
                    return r as StrictHttpResponse<TransactionResponse>;
                })
            );
    }

    /**
     * 取引取得API.
     *
     * 取引取得API
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `getTransaction$Response()` instead.
     *
     * This method doesn't expect any request body.
     */
    getTransaction(params: {
        /**
         * 店舗ID
         */
        shop_id: number;

        /**
         * 取引ID
         */
        transaction_id: number;
    }): Observable<TransactionResponse> {
        return this.getTransaction$Response(params).pipe(
            map((r: StrictHttpResponse<TransactionResponse>) => r.body as TransactionResponse)
        );
    }
}

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
import { TransactionsResponse } from "../models/transactions-response";

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
     * Path part for operation getTransactions
     */
    static readonly GetTransactionsPath = "/api/admin/shops/{shop_id}/transactions";

    /**
     * 取引リスト取得API.
     *
     * 取引リスト取得API
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `getTransactions()` instead.
     *
     * This method doesn't expect any request body.
     */
    getTransactions$Response(params: {
        /**
         * 店舗ID
         */
        shop_id: number;
    }): Observable<StrictHttpResponse<TransactionsResponse>> {
        const rb = new RequestBuilder(
            this.rootUrl,
            AdminTransactionService.GetTransactionsPath,
            "get"
        );
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
                    return r as StrictHttpResponse<TransactionsResponse>;
                })
            );
    }

    /**
     * 取引リスト取得API.
     *
     * 取引リスト取得API
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `getTransactions$Response()` instead.
     *
     * This method doesn't expect any request body.
     */
    getTransactions(params: {
        /**
         * 店舗ID
         */
        shop_id: number;
    }): Observable<TransactionsResponse> {
        return this.getTransactions$Response(params).pipe(
            map((r: StrictHttpResponse<TransactionsResponse>) => r.body as TransactionsResponse)
        );
    }

    /**
     * Path part for operation deleteTransactions
     */
    static readonly DeleteTransactionsPath = "/api/admin/shops/{shop_id}/transactions";

    /**
     * 全取引削除API.
     *
     * 全取引削除API
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `deleteTransactions()` instead.
     *
     * This method doesn't expect any request body.
     */
    deleteTransactions$Response(params: {
        /**
         * 店舗ID
         */
        shop_id: number;
    }): Observable<StrictHttpResponse<void>> {
        const rb = new RequestBuilder(
            this.rootUrl,
            AdminTransactionService.DeleteTransactionsPath,
            "delete"
        );
        if (params) {
            rb.path("shop_id", params.shop_id, {});
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
     * 全取引削除API.
     *
     * 全取引削除API
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `deleteTransactions$Response()` instead.
     *
     * This method doesn't expect any request body.
     */
    deleteTransactions(params: {
        /**
         * 店舗ID
         */
        shop_id: number;
    }): Observable<void> {
        return this.deleteTransactions$Response(params).pipe(
            map((r: StrictHttpResponse<void>) => r.body as void)
        );
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

    /**
     * Path part for operation deleteTransaction
     */
    static readonly DeleteTransactionPath =
        "/api/admin/shops/{shop_id}/transactions/{transaction_id}";

    /**
     * 取引削除API.
     *
     * 取引削除API
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `deleteTransaction()` instead.
     *
     * This method doesn't expect any request body.
     */
    deleteTransaction$Response(params: {
        /**
         * 店舗ID
         */
        shop_id: number;

        /**
         * 取引ID
         */
        transaction_id: number;
    }): Observable<StrictHttpResponse<void>> {
        const rb = new RequestBuilder(
            this.rootUrl,
            AdminTransactionService.DeleteTransactionPath,
            "delete"
        );
        if (params) {
            rb.path("shop_id", params.shop_id, {});
            rb.path("transaction_id", params.transaction_id, {});
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
     * 取引削除API.
     *
     * 取引削除API
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `deleteTransaction$Response()` instead.
     *
     * This method doesn't expect any request body.
     */
    deleteTransaction(params: {
        /**
         * 店舗ID
         */
        shop_id: number;

        /**
         * 取引ID
         */
        transaction_id: number;
    }): Observable<void> {
        return this.deleteTransaction$Response(params).pipe(
            map((r: StrictHttpResponse<void>) => r.body as void)
        );
    }
}

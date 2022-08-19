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
export class TransactionService extends BaseService {
    constructor(config: ApiConfiguration, http: HttpClient) {
        super(config, http);
    }

    /**
     * Path part for operation getLoginTransaction
     */
    static readonly GetLoginTransactionPath = "/api/transactions/me";

    /**
     * ログイン取引取得API.
     *
     * ログイン取引取得API
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `getLoginTransaction()` instead.
     *
     * This method doesn't expect any request body.
     */
    getLoginTransaction$Response(params?: {}): Observable<StrictHttpResponse<TransactionResponse>> {
        const rb = new RequestBuilder(
            this.rootUrl,
            TransactionService.GetLoginTransactionPath,
            "get"
        );
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
                    return r as StrictHttpResponse<TransactionResponse>;
                })
            );
    }

    /**
     * ログイン取引取得API.
     *
     * ログイン取引取得API
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `getLoginTransaction$Response()` instead.
     *
     * This method doesn't expect any request body.
     */
    getLoginTransaction(params?: {}): Observable<TransactionResponse> {
        return this.getLoginTransaction$Response(params).pipe(
            map((r: StrictHttpResponse<TransactionResponse>) => r.body as TransactionResponse)
        );
    }
}

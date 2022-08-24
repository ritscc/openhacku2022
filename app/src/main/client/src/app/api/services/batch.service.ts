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

/**
 * バッチ
 */
@Injectable({
    providedIn: "root",
})
export class BatchService extends BaseService {
    constructor(config: ApiConfiguration, http: HttpClient) {
        super(config, http);
    }

    /**
     * Path part for operation deleteExpiredTransaction
     */
    static readonly DeleteExpiredTransactionPath = "/api/batch/delete_expired_transactions";

    /**
     * 期限切れ取引削除API.
     *
     * 期限切れ取引削除API
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `deleteExpiredTransaction()` instead.
     *
     * This method doesn't expect any request body.
     */
    deleteExpiredTransaction$Response(params?: {}): Observable<StrictHttpResponse<void>> {
        const rb = new RequestBuilder(
            this.rootUrl,
            BatchService.DeleteExpiredTransactionPath,
            "post"
        );
        if (params) {
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
     * 期限切れ取引削除API.
     *
     * 期限切れ取引削除API
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `deleteExpiredTransaction$Response()` instead.
     *
     * This method doesn't expect any request body.
     */
    deleteExpiredTransaction(params?: {}): Observable<void> {
        return this.deleteExpiredTransaction$Response(params).pipe(
            map((r: StrictHttpResponse<void>) => r.body as void)
        );
    }
}

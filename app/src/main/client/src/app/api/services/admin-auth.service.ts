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

import { AdminLoginRequest } from "../models/admin-login-request";

/**
 * 認証
 */
@Injectable({
    providedIn: "root",
})
export class AdminAuthService extends BaseService {
    constructor(config: ApiConfiguration, http: HttpClient) {
        super(config, http);
    }

    /**
     * Path part for operation logout1
     */
    static readonly Logout1Path = "/api/admin/logout";

    /**
     * ログアウトAPI.
     *
     * ログアウトAPI
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `logout1()` instead.
     *
     * This method doesn't expect any request body.
     */
    logout1$Response(params?: {}): Observable<StrictHttpResponse<void>> {
        const rb = new RequestBuilder(this.rootUrl, AdminAuthService.Logout1Path, "post");
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
     * ログアウトAPI.
     *
     * ログアウトAPI
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `logout1$Response()` instead.
     *
     * This method doesn't expect any request body.
     */
    logout1(params?: {}): Observable<void> {
        return this.logout1$Response(params).pipe(
            map((r: StrictHttpResponse<void>) => r.body as void)
        );
    }

    /**
     * Path part for operation login1
     */
    static readonly Login1Path = "/api/admin/login";

    /**
     * ログインAPI.
     *
     * ログインAPI
     *
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `login1()` instead.
     *
     * This method sends `application/json` and handles request body of type `application/json`.
     */
    login1$Response(params: {
        /**
         * ログインリクエスト
         */
        body: AdminLoginRequest;
    }): Observable<StrictHttpResponse<void>> {
        const rb = new RequestBuilder(this.rootUrl, AdminAuthService.Login1Path, "post");
        if (params) {
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
     * ログインAPI.
     *
     * ログインAPI
     *
     * This method provides access to only to the response body.
     * To access the full response (for headers, for example), `login1$Response()` instead.
     *
     * This method sends `application/json` and handles request body of type `application/json`.
     */
    login1(params: {
        /**
         * ログインリクエスト
         */
        body: AdminLoginRequest;
    }): Observable<void> {
        return this.login1$Response(params).pipe(
            map((r: StrictHttpResponse<void>) => r.body as void)
        );
    }
}

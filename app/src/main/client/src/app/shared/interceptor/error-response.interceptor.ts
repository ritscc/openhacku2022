import { Injectable } from "@angular/core";
import {
    HttpErrorResponse,
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest,
} from "@angular/common/http";
import { Observable } from "rxjs";
import { catchError } from "rxjs/operators";
import { AlertService } from "@shared/service/alert.service";
import { Router } from "@angular/router";

type ErrorResponse = {
    code: number;
    message: string;
};

@Injectable()
export class ErrorResponseInterceptor implements HttpInterceptor {
    constructor(private alertService: AlertService, private router: Router) {}

    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
        return next.handle(request).pipe(
            catchError((error: HttpErrorResponse) => {
                const response = (
                    typeof error.error === "string" ? JSON.parse(error.error) : error.error
                ) as ErrorResponse;

                // 不正な認証情報
                if (error.status === 401) {
                    // adminがURLに含まれている場合は遷移先を変更する
                    const href: string = this.router.url;
                    let commands: string[] = ["/"];
                    if (href.match(/admin/)) {
                        commands = ["/", "admin", "login"];
                    }
                    this.router.navigate(commands, {
                        queryParams: { shop: null },
                        queryParamsHandling: "merge",
                    });
                } else if (error.status === 403) {
                    this.navigateErrorPage(403);
                }

                const message =
                    response.message ??
                    "予期しないエラーが発生しました。問題が解決しない場合は、管理者までお問い合わせください。";

                // APIが動作していない or 予期せぬエラーが発生した場合は500ページにリダイレクト
                if (response.code === undefined || response.code === 1000) {
                    this.navigateErrorPage(500);
                }

                // アラートメッセージを表示
                this.alertService.error(message);

                throw message;
            })
        );
    }

    /**
     * エラーページへ遷移
     *
     * @param status ステータスコード
     */
    navigateErrorPage(status: number) {
        this.router.navigate(["/error", status], {
            queryParamsHandling: "merge",
        });
    }
}

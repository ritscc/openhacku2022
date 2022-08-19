import { Component, Inject, OnInit } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { AuthService } from "@api/services/auth.service";
import { Router } from "@angular/router";
import { LoginRequest } from "@api/models/login-request";

type Data = {
    loginRequest: LoginRequest | undefined;
};

@Component({
    selector: "app-qr-loader-dialog",
    templateUrl: "./qr-loader-dialog.component.html",
    styleUrls: ["./qr-loader-dialog.component.scss"],
})
export class QrLoaderDialogComponent implements OnInit {
    constructor(
        @Inject(MAT_DIALOG_DATA) public data: Data,
        private matDialogRef: MatDialogRef<QrLoaderDialogComponent>,
        private authService: AuthService,
        private router: Router
    ) {}

    ngOnInit(): void {}

    /**
     * QRコード読み取り成功時のハンドラ
     *
     * @param content QRコードの中身
     */
    scanSuccessHandler(content: string) {
        this.matDialogRef.close();

        if (this.data.loginRequest) {
            this.data.loginRequest.shopId = Number(content);
            this.authService.login({ body: this.data.loginRequest }).subscribe(() => {
                this.router.navigate(["order"], { queryParamsHandling: "merge" });
            });
        }
    }
}

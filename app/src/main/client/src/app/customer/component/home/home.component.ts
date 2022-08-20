import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { FormBuilder, UntypedFormGroup, Validators } from "@angular/forms";
import { AlertService } from "@shared/service/alert.service";
import { AuthService } from "@api/services/auth.service";
import { Router } from "@angular/router";
import { LoginRequest } from "@api/models/login-request";

@Component({
    selector: "app-home",
    templateUrl: "./home.component.html",
    styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
    /**
     * フォーム
     */
    form!: UntypedFormGroup;

    /**
     * QRコードリーダーが起動しているか
     */
    isQRCodeReaderEnable: boolean = false;

    constructor(
        private matDialog: MatDialog,
        private formBuilder: FormBuilder,
        private alertService: AlertService,
        private authService: AuthService,
        private router: Router
    ) {}

    ngOnInit(): void {
        // フォームを作成
        this.form = this.formBuilder.group({
            numberOfPeople: [1, [Validators.required, Validators.min(1)]],
        });
    }

    /**
     * QRコードリーダーを起動
     */
    launchQRCodeReader(): void {
        if (this.form.invalid) {
            this.alertService.warn("来店人数を入力してください");
            return;
        }

        this.isQRCodeReaderEnable = true;
    }

    /**
     * QRコード読み取りに成功
     *
     * @param content QRコードの中身
     */
    successToScanQRCode(content: string) {
        const loginRequest: LoginRequest = {
            shopId: Number(content),
            numberOfPeople: this.form.value["numberOfPeople"],
        };

        this.authService.login({ body: loginRequest }).subscribe(() => {
            this.alertService.success("ログインしました");
            this.router.navigate(["dashboard"], { queryParamsHandling: "merge" });
        });
    }
}

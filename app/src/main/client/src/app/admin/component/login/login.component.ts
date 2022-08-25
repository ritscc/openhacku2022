import { Component, OnInit } from "@angular/core";
import { FormBuilder, UntypedFormGroup, Validators } from "@angular/forms";
import { AlertService } from "@shared/service/alert.service";
import { Router } from "@angular/router";
import { AdminLoginRequest } from "@api/models/admin-login-request";
import { AdminAuthService } from "@api/services/admin-auth.service";

@Component({
    selector: "app-login",
    templateUrl: "./login.component.html",
    styleUrls: ["./login.component.scss"],
})
export class LoginComponent implements OnInit {
    hide: boolean = true;

    form!: UntypedFormGroup;

    constructor(
        private formBuilder: FormBuilder,
        private alertService: AlertService,
        private adminAuthService: AdminAuthService,
        private router: Router
    ) {}

    ngOnInit(): void {
        // フォームを作成
        this.form = this.formBuilder.group({
            code: [null, [Validators.required]],
            password: [null, [Validators.required]],
        });
    }

    /**
     * ログインする
     */
    login(): void {
        if (this.form.valid) {
            const adminLoginRequest: AdminLoginRequest = {
                code: this.form.value["code"],
                password: this.form.value["password"],
            };

            this.adminAuthService.login1({ body: adminLoginRequest }).subscribe(() => {
                this.alertService.success("ログインしました");
                this.router.navigate(["admin/orders"], { queryParamsHandling: "merge" });
            });
        } else {
            this.alertService.warn("正しいコードを入力してください");
        }
    }
}

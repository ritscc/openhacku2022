import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { QrLoaderDialogComponent } from "@shared/component/qr-loader-dialog/qr-loader-dialog.component";
import { FormBuilder, UntypedFormGroup, Validators } from "@angular/forms";
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

    constructor(private matDialog: MatDialog, private formBuilder: FormBuilder) {}

    ngOnInit(): void {
        // フォームを作成
        this.form = this.formBuilder.group({
            numberOfPeople: [null, [Validators.required, Validators.min(0)]],
        });
    }

    /**
     * ログインボタンをクリック
     */
    onClickLogin(): void {
        const loginRequest: LoginRequest = {
            numberOfPeople: this.form.value["numberOfPeople"],
            shopId: 0,
        };
        this.matDialog.open(QrLoaderDialogComponent, { data: { loginRequest: loginRequest } });
    }
}

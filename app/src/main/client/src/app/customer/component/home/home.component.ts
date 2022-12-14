import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { FormBuilder, UntypedFormGroup, Validators } from "@angular/forms";
import { AlertService } from "@shared/service/alert.service";
import { AuthService } from "@api/services/auth.service";
import { ActivatedRoute, Router, UrlSerializer, UrlTree } from "@angular/router";
import { LoginRequest } from "@api/models/login-request";
import { environment } from "src/environments/environment";

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

    /**
     * QRコードリーダーで読み取る必要があるかどうか
     */
    isQRCodeReaderRequire: boolean = false;
    preShopId!: number;

    constructor(
        private matDialog: MatDialog,
        private formBuilder: FormBuilder,
        private alertService: AlertService,
        private authService: AuthService,
        private router: Router,
        private route: ActivatedRoute,
        private urlSerializer: UrlSerializer
    ) {}

    ngOnInit(): void {
        // フォームを作成
        this.form = this.formBuilder.group({
            numberOfPeople: [1, [Validators.required, Validators.min(1)]],
        });

        // 現在のURLを取得してプロパティを書き換える
        this.route.queryParams.subscribe((params) => {
            const shopId: string | undefined = params["shop_id"];
            if (shopId) {
                this.isQRCodeReaderRequire = false;
                this.preShopId = Number(shopId);
            } else {
                this.isQRCodeReaderRequire = true;
            }
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
        // QRコードに書かれているURLからクエリパラメータのshopIdを取り出す
        const url: string = content.replace("https://", "").replace("http://", "");
        const tree: UrlTree = this.urlSerializer.parse(url);
        const shopId: string | undefined = tree.queryParams["shop_id"];

        if (!shopId) {
            this.alertService.warn("不正なQRコードです");
            return;
        }

        this.login(Number(shopId));
    }

    /**
     * 入店に成功 (入店ボタンをクリックした場合に実行)
     */
    successToEnterShop(): void {
        this.login(this.preShopId);
    }

    /**
     * ログイン処理
     * @param shopId ショップのID
     */
    login(shopId: number): void {
        if (this.form.valid) {
            const loginRequest: LoginRequest = {
                shopId: shopId,
                numberOfPeople: this.form.value["numberOfPeople"],
            };

            this.authService.login({ body: loginRequest }).subscribe(() => {
                localStorage.setItem(environment.SHOW_TABLE_NUMBER_KEY, "enable");
                this.alertService.success("ログインしました");
                this.router.navigate(["dashboard"], { queryParamsHandling: "merge" });
            });
        } else {
            this.alertService.warn("来客人数を入力してください");
        }
    }
}

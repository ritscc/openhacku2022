import { Component, OnInit } from "@angular/core";
import { FormBuilder, UntypedFormGroup, Validators } from "@angular/forms";
import { AdminShopService } from "@api/services/admin-shop.service";
import { AdminMenuService } from "@api/services/admin-menu.service";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";
import { AlertService } from "@shared/service/alert.service";
import { Router } from "@angular/router";
import { DomSanitizer, SafeUrl } from "@angular/platform-browser";
import { MenuUpsertRequest } from "@api/models/menu-upsert-request";

@UntilDestroy()
@Component({
    selector: "app-menu-create-form",
    templateUrl: "./menu-create-form.component.html",
    styleUrls: ["./menu-create-form.component.scss"],
})
export class MenuCreateFormComponent implements OnInit {
    /**
     * メニュー作成フォーム
     */
    form!: UntypedFormGroup;

    /**
     * 商品画像src
     */
    imageSrc: SafeUrl | undefined;

    /**
     * 商品画像
     */
    image: string | undefined;

    constructor(
        private formBuilder: FormBuilder,
        private adminShopService: AdminShopService,
        private adminMenuService: AdminMenuService,
        private alertService: AlertService,
        private router: Router,
        private sanitizer: DomSanitizer
    ) {}

    ngOnInit(): void {
        // フォームを作成
        this.form = this.formBuilder.group({
            name: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
            price: [null, [Validators.required, Validators.min(1)]],
        });
    }

    onSubmit(): void {
        if (this.form.valid) {
            if (!this.image) {
                this.alertService.warn("画像を選択してください");
                return;
            }

            const requestBody: MenuUpsertRequest = {
                name: this.form.value["name"],
                price: this.form.value["price"],
                image: this.image,
            };

            this.adminShopService
                .getShop()
                .pipe(untilDestroyed(this))
                .subscribe((shop) => {
                    this.adminMenuService
                        .createMenu({ shop_id: shop.id, body: requestBody })
                        .subscribe(() => {
                            this.router.navigate(["admin", "menus"]);
                            this.alertService.success("メニューを作成しました");
                        });
                });
        }
    }

    onCancel(): void {
        this.router.navigate(["admin", "menus"]);
    }

    onClickFileSelectButton(event: Event): void {
        // chromeで同じファイルを連続で選択できるようにするため
        (event.target as HTMLInputElement).value = "";
    }

    async onSelectFile(event: Event) {
        const files: FileList | null = (event.target as HTMLInputElement).files;
        if (!files) {
            return;
        }

        const reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onload = (ev: any) => {
            const content = ev.target.result;
            this.imageSrc = this.sanitizer.bypassSecurityTrustUrl(content);
            this.image = this.getEncodedImage(content);
        };
    }

    getEncodedImage(src: string) {
        return src.slice(src.indexOf(",") + 1);
    }
}

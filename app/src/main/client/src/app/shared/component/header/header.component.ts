import { Component, OnInit } from "@angular/core";
import { NavigationEnd, Router } from "@angular/router";
import { filter } from "rxjs/operators";
import { AdminAuthService } from "@api/services/admin-auth.service";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";
import { AlertService } from "@shared/service/alert.service";
import { TitleService } from "@shared/service/title.service";

type Nav = {
    label: string;
    icon: string;
    url: string | undefined;
};

@UntilDestroy()
@Component({
    selector: "app-header",
    templateUrl: "./header.component.html",
    styleUrls: ["./header.component.scss"],
})
export class HeaderComponent implements OnInit {
    /**
     * ヘッダータイトル
     */
    title!: string;

    /**
     * 管理者フラグ
     */
    isAdmin: boolean = false;

    /**
     * ナビリスト(顧客)
     */
    customerNavs: Nav[] = [
        {
            label: "メニュー",
            icon: "menu_book",
            url: "/dashboard",
        },
        {
            label: "お会計",
            icon: "shopping_basket",
            url: "/dashboard/payment",
        },
        {
            label: "店員呼出",
            icon: "campaign",
            url: undefined,
        },
    ];

    /**
     * ナビリスト(管理者)
     */
    adminNavs: Nav[] = [
        {
            label: "メニュー",
            icon: "menu_book",
            url: "/admin/menus",
        },
        {
            label: "注文状況",
            icon: "list_alt",
            url: "/admin/orders",
        },
    ];

    /**
     * サイドナビを有効化するかどうか
     */
    isSidenavValid: boolean = false;

    constructor(
        private router: Router,
        private adminAuthService: AdminAuthService,
        private alertService: AlertService,
        private titleService: TitleService
    ) {
        // ナビゲーション終了時にサイドナビの状態を定義
        this.router.events
            .pipe(filter((e) => e instanceof NavigationEnd))
            .subscribe(() => this.setIsSidenavValid());
    }

    ngOnInit(): void {
        this.isAdmin = this.router.url.split("/")[1] === "admin";

        this.titleService.getTitle().subscribe((title) => {
            this.title = title;
        });
    }

    /**
     * サイドナビの状態をセットする
     */
    setIsSidenavValid(): void {
        this.isSidenavValid = this.router.url !== "/";
    }

    logout(): void {
        this.adminAuthService
            .logout1()
            .pipe(untilDestroyed(this))
            .subscribe(() => {
                this.router.navigate(["admin", "login"]);
                this.alertService.success("ログアウトしました");
            });
    }
}

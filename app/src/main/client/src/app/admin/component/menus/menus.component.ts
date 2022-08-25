import { Component, OnInit } from "@angular/core";
import { AdminMenuService } from "@api/services/admin-menu.service";
import { MenuResponse } from "@api/models/menu-response";
import { AdminShopService } from "@api/services/admin-shop.service";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";
import { AlertService } from "@shared/service/alert.service";

@UntilDestroy()
@Component({
    selector: "app-menus",
    templateUrl: "./menus.component.html",
    styleUrls: ["./menus.component.scss"],
})
export class MenusComponent implements OnInit {
    menus: MenuResponse[] = [];

    constructor(
        private adminShopService: AdminShopService,
        private adminMenuService: AdminMenuService,
        private alertService: AlertService
    ) {}

    ngOnInit(): void {
        // メニューリストを取得
        this.loadMenus();
    }

    /**
     * メニューを編集
     *
     * @param menu メニュー
     */
    editMenu(menu: MenuResponse): void {
        this.alertService.warn("その機能は未実装です");
    }

    /**
     * メニューを削除
     *
     * @param menu メニュー
     */
    deleteMenu(menu: MenuResponse): void {
        this.alertService
            .confirm("削除確認", "本当にメニューを削除しますか？")
            .subscribe((result) => {
                if (!result) {
                    return;
                }

                this.adminShopService
                    .getShop()
                    .pipe(untilDestroyed(this))
                    .subscribe((shop) => {
                        this.adminMenuService
                            .deleteMenu({ shop_id: shop.id, menu_id: menu.id })
                            .pipe(untilDestroyed(this))
                            .subscribe((response) => {
                                this.alertService.success("メニューを削除しました");
                                this.loadMenus();
                            });
                    });
            });
    }

    /**
     * メニューリストを読み込む
     */
    loadMenus(): void {
        this.adminShopService
            .getShop()
            .pipe(untilDestroyed(this))
            .subscribe((shop) => {
                this.adminMenuService
                    .getMenus1({ shop_id: shop.id })
                    .pipe(untilDestroyed(this))
                    .subscribe((response) => {
                        this.menus = response.menus;
                    });
            });
    }
}

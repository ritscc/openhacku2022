import { Component, OnInit } from "@angular/core";
import { MenuResponse } from "@api/models/menu-response";
import { MenuService } from "@api/services/menu.service";
import { TransactionService } from "@api/services/transaction.service";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";

@UntilDestroy()
@Component({
    selector: "app-menus",
    templateUrl: "./menus.component.html",
    styleUrls: ["./menus.component.scss"],
})
export class MenusComponent implements OnInit {
    /**
     * メニューリスト
     */
    menus: MenuResponse[] = [];

    constructor(private transactionService: TransactionService, private menuService: MenuService) {}

    ngOnInit(): void {
        // メニューリストを取得
        this.transactionService
            .getLoginTransaction()
            .pipe(untilDestroyed(this))
            .subscribe((response) => {
                this.menuService
                    .getMenus({ shop_id: response.shopId })
                    .pipe(untilDestroyed(this))
                    .subscribe((response) => {
                        this.menus = response.menus;
                    });
            });
    }
}

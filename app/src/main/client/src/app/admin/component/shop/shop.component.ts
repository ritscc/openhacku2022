import { Component, OnInit } from "@angular/core";
import { AdminShopService } from "@api/services/admin-shop.service";
import { ShopResponse } from "@api/models/shop-response";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";
import { TableResponse } from "@api/models/table-response";
import { AlertService } from "@shared/service/alert.service";

@UntilDestroy()
@Component({
    selector: "app-shop",
    templateUrl: "./shop.component.html",
    styleUrls: ["./shop.component.scss"],
})
export class ShopComponent implements OnInit {
    /**
     * ログイン店舗
     */
    shop!: ShopResponse;

    /**
     * テーブルリスト
     */
    tables: TableResponse[] = [];

    constructor(private adminShopService: AdminShopService, private alertService: AlertService) {}

    ngOnInit(): void {
        this.adminShopService
            .getShop()
            .pipe(untilDestroyed(this))
            .subscribe((response) => {
                this.shop = response;
                this.tables = response.tables.sort((a, b) =>
                    a.tableNumber > b.tableNumber ? 1 : -1
                );
                this.tables[1].isUsed = true;
            });
    }

    onClickEditShop(): void {
        this.alertService.warn("その機能は未実装です");
    }

    onClickEditTables(): void {
        this.alertService.warn("その機能は未実装です");
    }
}

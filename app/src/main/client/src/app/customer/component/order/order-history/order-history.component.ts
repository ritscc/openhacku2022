import { Component, OnInit } from "@angular/core";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";
import { TransactionService } from "@api/services/transaction.service";
import { OrderMenuResponse } from "@api/models/order-menu-response";

@UntilDestroy()
@Component({
    selector: "app-order-history",
    templateUrl: "./order-history.component.html",
    styleUrls: ["./order-history.component.scss"],
})
export class OrderHistoryComponent implements OnInit {
    menus: OrderMenuResponse[] = [];

    constructor(private transactionService: TransactionService) {}

    ngOnInit(): void {
        this.menus = [];
        // 購入履歴を取得
        this.transactionService
            .getLoginTransaction()
            .pipe(untilDestroyed(this))
            .subscribe((response) => {
                response.orders.forEach((order) => {
                    order.menus.forEach((menu) => {
                        this.menus.push(menu);
                    });
                });
            });
    }
}

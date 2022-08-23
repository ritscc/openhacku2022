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
        this.transactionService
            .getLoginTransaction()
            .pipe(untilDestroyed(this))
            .subscribe((response) => {
                this.menus = [];
                response.orders.forEach((order) => {
                    order.menus.forEach((menu) => {
                        this.addMenu(menu);
                    });
                });
            });
    }

    /**
     * menusにメニューを追加 このとき同じメニューの重複を削除
     * @param menu 追加するメニューオブジェクト
     */
    addMenu(menu: OrderMenuResponse): void {
        const existingMenu = this.menus.filter((element) => element.id === menu.id)[0];
        if (existingMenu) {
            this.menus[this.menus.indexOf(existingMenu)].quantity += menu.quantity;
        } else {
            this.menus.push(menu);
        }
    }
}

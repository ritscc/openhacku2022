import { Component, OnInit, ViewChild } from "@angular/core";
import { MenusComponent } from "@customer/component/order/menus/menus.component";
import { CartComponent } from "@customer/component/order/cart/cart.component";
import { OrderHistoryComponent } from "@customer/component/order/order-history/order-history.component";
import { MatDialog } from "@angular/material/dialog";
import { TableNumberDialogComponent } from "@customer/component/table-number-dialog/table-number-dialog.component";
import { TransactionService } from "@api/services/transaction.service";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";
import { CartService } from "@customer/service/cart.service";

@UntilDestroy()
@Component({
    selector: "app-order",
    templateUrl: "./order.component.html",
    styleUrls: ["./order.component.scss"],
})
export class OrderComponent implements OnInit {
    @ViewChild(MenusComponent) menusComponent!: MenusComponent;
    @ViewChild(CartComponent) cartComponent!: CartComponent;
    @ViewChild(OrderHistoryComponent) orderHistoryComponent!: OrderHistoryComponent;

    /**
     * カートに入ったメニューリスト
     */
    numberOfMenusInCart: number = 0;

    constructor(
        private matDialog: MatDialog,
        private transactionService: TransactionService,
        private cartService: CartService
    ) {}

    ngOnInit(): void {
        // テーブル番号を取得 & 表示
        this.transactionService
            .getLoginTransaction()
            .pipe(untilDestroyed(this))
            .subscribe((response) => {
                this.matDialog.open(TableNumberDialogComponent, {
                    data: { tableNumber: response.tableId },
                });
            });

        // カートに入ったメニュー数を取得
        this.cartService
            .getMenus()
            .pipe(untilDestroyed(this))
            .subscribe((menus) => {
                this.numberOfMenusInCart = menus
                    .map((menu) => menu.quantity)
                    .reduce((accumulator, current) => {
                        return accumulator + current;
                    }, 0);
            });
    }

    /**
     * タブ切り替え時に呼ばれる
     */
    switchTab(): void {
        this.menusComponent.ngOnInit();
        this.cartComponent.ngOnInit();
        this.orderHistoryComponent.ngOnInit();
    }
}

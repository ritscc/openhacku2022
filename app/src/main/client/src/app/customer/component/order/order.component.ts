import { Component, OnInit, ViewChild } from "@angular/core";
import { MenusComponent } from "@customer/component/order/menus/menus.component";
import { CartComponent } from "@customer/component/order/cart/cart.component";
import { OrderHistoryComponent } from "@customer/component/order/order-history/order-history.component";
import { MatDialog } from "@angular/material/dialog";
import { TableNumberDialogComponent } from "@customer/component/table-number-dialog/table-number-dialog.component";
import { TransactionService } from "@api/services/transaction.service";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";
import { CartService } from "@customer/service/cart.service";
import { environment } from "src/environments/environment";

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
        if (localStorage.getItem(environment.SHOW_TABLE_NUMBER_KEY) === "enable") {
            this.transactionService
                .getLoginTransaction()
                .pipe(untilDestroyed(this))
                .subscribe((response) => {
                    this.matDialog.open(TableNumberDialogComponent, {
                        data: { shopName: response.shopName, tableNumber: response.tableNumber },
                    });
                    localStorage.setItem(environment.SHOW_TABLE_NUMBER_KEY, "disable");
                });
        }

        this.loadNumberOfMenusInCart();
    }

    /**
     * タブ切り替え時に呼ばれる
     */
    switchTab(): void {
        this.menusComponent.ngOnInit();
        this.cartComponent.ngOnInit();
        this.orderHistoryComponent.ngOnInit();
    }

    /**
     * カートの中身変更イベントを通知するEventEmitter
     */
    cartEventEmitter(): void {
        this.loadNumberOfMenusInCart();
    }

    /**
     * カートに入ったメニュー数を取得
     */
    loadNumberOfMenusInCart(): void {
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
}

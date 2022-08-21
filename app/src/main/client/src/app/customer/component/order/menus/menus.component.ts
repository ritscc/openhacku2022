import { Component, OnInit } from "@angular/core";
import { MenuResponse } from "@api/models/menu-response";
import { MenuService } from "@api/services/menu.service";
import { TransactionService } from "@api/services/transaction.service";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";
import { AlertService } from "@shared/service/alert.service";
import { CartService } from "@customer/service/cart.service";
import { OrderCreateRequest } from "@api/models/order-create-request";
import { OrderService } from "@api/services/order.service";

type Menu = MenuResponse & {
    isChecked: boolean;
    quantity: number;
};

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
    menus: Menu[] = [];

    constructor(
        private transactionService: TransactionService,
        private menuService: MenuService,
        private alertService: AlertService,
        private cartService: CartService,
        private orderService: OrderService
    ) {}

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
                        this.menus = response.menus.map((menu) => {
                            let result = menu as Menu;
                            result.isChecked = false;
                            result.quantity = 1;
                            return result;
                        });
                    });
            });
    }

    /**
     * メニューをカートに追加
     */
    addMenusToCart() {
        const menus = this.menus.filter((menu) => menu.isChecked && menu.quantity > 0);
        if (menus.length === 0) {
            this.alertService.warn("メニューが選択されていません");
            return;
        } else {
            const savedMenus = menus.map((menu) => {
                return { menuId: menu.id, quantity: menu.quantity };
            });
            this.cartService.addMenus(savedMenus).subscribe(() => {
                // 成功を通知 & メニュー選択状態をリセット
                this.resetMenuSelection();
                this.alertService.success("カートに追加しました");
            });
        }
    }

    /**
     * 注文する
     */
    order() {
        const requestBody: OrderCreateRequest = {
            menus: this.menus
                .filter((menu) => menu.isChecked && menu.quantity > 0)
                .map((menu) => {
                    return { menuId: menu.id, quantity: menu.quantity };
                }),
        };

        this.transactionService
            .getLoginTransaction()
            .pipe(untilDestroyed(this))
            .subscribe((transaction) => {
                this.orderService
                    .createOrder({
                        shop_id: transaction.shopId,
                        body: requestBody,
                    })
                    .pipe(untilDestroyed(this))
                    .subscribe(() => {
                        this.resetMenuSelection();
                        this.alertService.success("注文が完了しました");
                    });
            });
    }

    /**
     * メニュー選択状態をリセット
     */
    resetMenuSelection(): void {
        this.menus.forEach((menu) => {
            menu.isChecked = false;
            menu.quantity = 1;
        });
    }
}

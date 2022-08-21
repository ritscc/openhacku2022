import { Component, OnInit } from "@angular/core";
import { TransactionService } from "@api/services/transaction.service";
import { MenuService } from "@api/services/menu.service";
import { AlertService } from "@shared/service/alert.service";
import { CartService } from "@customer/service/cart.service";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";
import { MenuResponse } from "@api/models/menu-response";
import { OrderCreateRequest } from "@api/models/order-create-request";
import { OrderService } from "@api/services/order.service";

type Menu = MenuResponse & {
    quantity: number;
};

@UntilDestroy()
@Component({
    selector: "app-cart",
    templateUrl: "./cart.component.html",
    styleUrls: ["./cart.component.scss"],
})
export class CartComponent implements OnInit {
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
                        this.cartService
                            .getMenus()
                            .pipe(untilDestroyed(this))
                            .subscribe((menus) => {
                                this.menus = response.menus
                                    .filter((menu) =>
                                        menus.map((element) => element.menuId).includes(menu.id)
                                    )
                                    .map((menu) => {
                                        let result = menu as Menu;
                                        result.quantity = menus.filter(
                                            (element) => element.menuId === menu.id
                                        )[0].quantity;
                                        return result;
                                    });
                            });
                    });
            });
    }

    /**
     * メニューを削除する
     *
     * @param menu メニュー
     */
    deleteMenu(menu: Menu) {
        this.alertService
            .confirm("削除確認", "このメニューを削除しますか？")
            .subscribe((result) => {
                if (result) {
                    this.menus = this.menus.filter((element) => element.id !== menu.id);
                    this.cartService.deleteMenu(menu);
                    this.alertService.success("カートから削除しました");
                }
            });
    }

    changeMenuQuantity(menu: Menu) {
        this.cartService.updateMenu({ menuId: menu.id, quantity: menu.quantity });
        this.alertService.success("注文数を変更しました");
    }

    /**
     * 注文する
     */
    order() {
        const requestBody: OrderCreateRequest = {
            menus: this.menus.map((menu) => {
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
                        this.menus = [];
                        this.cartService.deleteAll();
                        this.alertService.success("注文が完了しました");
                    });
            });
    }
}

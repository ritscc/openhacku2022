import { Component, OnInit } from "@angular/core";
import { AdminTransactionService } from "@api/services/admin-transaction.service";
import { AdminShopService } from "@api/services/admin-shop.service";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";
import { AdminOrderService } from "@api/services/admin-order.service";
import { AlertService } from "@shared/service/alert.service";
import { OrderMenuResponse } from "@api/models";
import { OrderStatusEnum } from "@shared/enums/order-status.enum";

type OrderMenu = OrderMenuResponse & {
    orderId: number;
    orderedDate: string;
    tableNumber: number;
};

@UntilDestroy()
@Component({
    selector: "app-orders",
    templateUrl: "./orders.component.html",
    styleUrls: ["./orders.component.scss"],
})
export class OrdersComponent implements OnInit {
    orderMenus: OrderMenu[] = [];
    numberOfCompleteOrders: number = 0;

    constructor(
        private adminTransactionService: AdminTransactionService,
        private adminOrderService: AdminOrderService,
        private adminShopService: AdminShopService,
        private alertService: AlertService
    ) {}

    ngOnInit(): void {
        this.getOrders();
    }

    /**
     * 商品情報を取得する
     */
    getOrders(): void {
        this.orderMenus = [];
        // 商品リストを取得する
        this.adminShopService
            .getShop()
            .pipe(untilDestroyed(this))
            .subscribe((shop) => {
                this.adminTransactionService
                    .getTransactions({ shop_id: shop.id })
                    .pipe(untilDestroyed(this))
                    .subscribe((transactions) => {
                        transactions.transactions.map((transaction) => {
                            transaction.orders.map((order) => {
                                this.orderMenus = this.orderMenus.concat(
                                    order.menus.map((menu) => {
                                        const result = menu as OrderMenu;
                                        result["orderId"] = order.id;
                                        result["orderedDate"] = order.orderedDate;
                                        result["tableNumber"] = transaction.tableNumber;
                                        return result;
                                    })
                                );
                            });
                        });
                    });
            });
    }

    /**
     * 準備中のオーダーを取得する
     */
    getInCompleteOrders(): OrderMenu[] {
        const orders = this.orderMenus.filter(
            (orderMenu) => orderMenu.status !== OrderStatusEnum.COMPLETED
        );
        this.numberOfCompleteOrders = orders.length;
        return orders;
    }

    /**
     * 提供済のオーダーを取得する
     */
    getInCompletedOrders(): OrderMenu[] {
        return this.orderMenus.filter((order) => order.status === OrderStatusEnum.COMPLETED);
    }

    /**
     * 注文ステータスを準備中から提供済に更新する
     * @param orderMenu
     */
    updateOrder(orderMenu: OrderMenu): void {
        this.adminShopService
            .getShop()
            .pipe(untilDestroyed(this))
            .subscribe((shop) => {
                this.adminOrderService
                    .updateOrderStatus({
                        shop_id: shop.id,
                        order_id: orderMenu.orderId,
                        menu_id: orderMenu.id,
                        body: { status: OrderStatusEnum.COMPLETED },
                    })
                    .pipe(untilDestroyed(this))
                    .subscribe(() => {
                        this.ngOnInit();
                        this.alertService.success("注文ステータスを更新しました");
                    });
            });
    }

    /**
     * タブ切り替え時に呼ばれる
     */
    switchTab(): void {}

    /**
     * 準備中タグに入った商品数を取得
     * TODO: バッチ未作成であるため、バッチを作成したらこの関数も作成する
     */
    loadNumberOfCompleteOrders(): void {}

    //     this.cartService
    //         .getMenus()
    //         .pipe(untilDestroyed(this))
    //         .subscribe((menus) => {
    //             this.numberOfMenusInCart = menus
    //                 .map((menu) => menu.quantity)
    //                 .reduce((accumulator, current) => {
    //                     return accumulator + current;
    //                 }, 0);
    //         });
    // }
}

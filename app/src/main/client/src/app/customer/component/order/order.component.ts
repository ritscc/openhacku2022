import { Component, OnInit, ViewChild } from "@angular/core";
import { MenusComponent } from "@customer/component/order/menus/menus.component";
import { CartComponent } from "@customer/component/order/cart/cart.component";
import { OrderHistoryComponent } from "@customer/component/order/order-history/order-history.component";

@Component({
    selector: "app-order",
    templateUrl: "./order.component.html",
    styleUrls: ["./order.component.scss"],
})
export class OrderComponent implements OnInit {
    @ViewChild(MenusComponent) menusComponent!: MenusComponent;
    @ViewChild(CartComponent) cartComponent!: CartComponent;
    @ViewChild(OrderHistoryComponent) orderHistoryComponent!: OrderHistoryComponent;

    constructor() {}

    ngOnInit(): void {}

    /**
     * タブ切り替え時に呼ばれる
     */
    switchTab(): void {
        this.menusComponent.ngOnInit();
        this.cartComponent.ngOnInit();
        this.orderHistoryComponent.ngOnInit();
    }
}

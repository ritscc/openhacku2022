import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { TransactionResponse } from "@api/models/transaction-response";
import { TransactionService } from "@api/services/transaction.service";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";
import { Router } from "@angular/router";
import { AlertService } from "@shared/service/alert.service";
import { CheckoutService } from "@api/services/checkout.service";

type OrderMenu = {
    name: string;
    price: number;
};

@UntilDestroy()
@Component({
    selector: "app-payment",
    templateUrl: "./payment.component.html",
    styleUrls: ["./payment.component.scss"],
})
export class PaymentComponent implements OnInit {
    /**
     * 取引
     */
    transaction!: TransactionResponse;

    menus: OrderMenu[] = [];

    constructor(
        public dialog: MatDialog,
        private transactionService: TransactionService,
        private router: Router,
        private alertService: AlertService,
        private checkoutService: CheckoutService
    ) {}

    ngOnInit(): void {
        this.transactionService
            .getLoginTransaction()
            .pipe(untilDestroyed(this))
            .subscribe((response) => {
                this.transaction = response;

                if (response.orders.length === 0) {
                    this.alertService.warn("注文を完了してください");
                    this.router.navigate(["dashboard"]);
                }

                response.orders.forEach((order) => {
                    order.menus.forEach((menu) => {
                        for (let i = 0; i < menu.quantity; i++) {
                            this.menus.push({ name: menu.name, price: menu.price });
                        }
                    });
                });
            });
    }

    /**
     * 決済する
     */
    onClickPayment(): void {
        this.checkoutService
            .checkout({})
            .pipe(untilDestroyed(this))
            .subscribe((response) => {
                window.location.href = response.redirectUrl;
            });
    }

    /**
     * 合計金額を返す
     *
     * @return 合計金額
     */
    getTotalPrice(): number {
        return this.menus
            .map((menu) => menu.price)
            .reduce((accumulator, current) => {
                return accumulator + current;
            }, 0);
    }

    /**
     * 消費税を返す
     */
    getTaxAmount(): number {
        return this.getTotalPrice() * 0.1;
    }
}

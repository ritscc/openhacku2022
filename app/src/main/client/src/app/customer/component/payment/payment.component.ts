import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { QrDialogComponent } from "@customer/component/qr-dialog/qr-dialog.component";
import { TransactionResponse } from "@api/models/transaction-response";
import { TransactionService } from "@api/services/transaction.service";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";

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

    constructor(public dialog: MatDialog, private transactionService: TransactionService) {}

    ngOnInit(): void {
        this.transactionService
            .getLoginTransaction()
            .pipe(untilDestroyed(this))
            .subscribe((response) => {
                this.transaction = response;

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
     * QRコードダイアログを開く
     *
     * @param QRコード情報に載せたい文字列
     */
    openQrcodeDialog(content: string): void {
        this.dialog.open(QrDialogComponent, { data: content });
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

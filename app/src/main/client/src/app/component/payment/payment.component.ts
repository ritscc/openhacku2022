import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { QrDialogComponent } from "@app/component/qr-dialog/qr-dialog.component";

@Component({
    selector: "app-payment",
    templateUrl: "./payment.component.html",
    styleUrls: ["./payment.component.scss"],
})
export class PaymentComponent implements OnInit {
    constructor(public dialog: MatDialog) {}

    openQrcodeDialog(): void {
        this.dialog.open(QrDialogComponent, { data: "1" });
    }

    ngOnInit(): void {}
}

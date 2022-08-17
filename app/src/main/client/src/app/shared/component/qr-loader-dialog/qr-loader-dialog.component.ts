import { Component, Inject, OnInit } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";

@Component({
    selector: "app-qr-loader-dialog",
    templateUrl: "./qr-loader-dialog.component.html",
    styleUrls: ["./qr-loader-dialog.component.scss"],
})
export class QrLoaderDialogComponent implements OnInit {
    constructor(
        @Inject(MAT_DIALOG_DATA) public data: any,
        private matDialogRef: MatDialogRef<QrLoaderDialogComponent>
    ) {}

    ngOnInit(): void {}

    scanSuccessHandler(content: string) {
        this.matDialogRef.close("OK");

        // TODO: 読み取り成功後の処理を呼び出す
        console.log(`QRコード読み取れたよ: ${content}`);
    }
}

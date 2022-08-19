import { Component, Inject, OnInit } from "@angular/core";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";

@Component({
    selector: "app-qr-dialog",
    templateUrl: "./qr-dialog.component.html",
    styleUrls: ["./qr-dialog.component.scss"],
})
export class QrDialogComponent implements OnInit {
    // NOTE: 定数の書き方がこれで合っているか分からない
    CENTER_IMAGE_SRC: string = "./assets/angular-logo.png" as const;

    /**
     * コンストラクタ
     * @param strInQrcode QRコードが保持したい文字列
     */
    constructor(
        @Inject(MAT_DIALOG_DATA)
        public strInQrcode: string
    ) {}

    ngOnInit(): void {}
}

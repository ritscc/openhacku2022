import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { QrLoaderDialogComponent } from "@shared/component/qr-loader-dialog/qr-loader-dialog.component";

@Component({
    selector: "app-home",
    templateUrl: "./home.component.html",
    styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
    numPeopleList: number[] = [1, 2, 3, 4];

    constructor(private matDialog: MatDialog) {}

    ngOnInit(): void {}

    /**
     * ログインボタンをクリック
     */
    onClickLogin(): void {
        // TODO: ログイン処理（QRコードリーダーを起動）
        this.matDialog.open(QrLoaderDialogComponent);
        console.log("ログインボタンをクリックしたよ");
    }
}

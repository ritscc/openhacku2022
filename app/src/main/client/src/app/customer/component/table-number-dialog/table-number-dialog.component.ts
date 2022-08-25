import { Component, Inject, OnInit } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";

type DialogData = {
    shopName: string;
    tableNumber: number;
};

@Component({
    selector: "app-table-number-dialog",
    templateUrl: "./table-number-dialog.component.html",
    styleUrls: ["./table-number-dialog.component.scss"],
})
export class TableNumberDialogComponent implements OnInit {
    /**
     * 店舗名
     */
    shopName!: string;

    /**
     * テーブル番号
     */
    tableNumber!: number;

    constructor(
        public dialogRef: MatDialogRef<TableNumberDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: DialogData
    ) {}

    ngOnInit(): void {
        this.shopName = this.data.shopName;
        this.tableNumber = this.data.tableNumber;
    }
}

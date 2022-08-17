import { Component, Inject, OnInit } from "@angular/core";
import { MAT_SNACK_BAR_DATA, MatSnackBarRef } from "@angular/material/snack-bar";
import { AlertModel } from "@shared/model/alert.model";

@Component({
    selector: "app-snack-bar",
    templateUrl: "./snack-bar.component.html",
    styleUrls: ["./snack-bar.component.scss"],
})
export class SnackBarComponent implements OnInit {
    constructor(
        public snackBarRef: MatSnackBarRef<SnackBarComponent>,
        @Inject(MAT_SNACK_BAR_DATA) public data: AlertModel
    ) {}

    ngOnInit(): void {}

    /**
     * スナックバーを閉じる
     */
    dismiss() {
        this.snackBarRef.dismiss();
    }
}

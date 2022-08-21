import { Component, Inject, OnInit } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";

@Component({
    selector: "app-confirm-dialog",
    templateUrl: "./confirm-dialog.component.html",
    styleUrls: ["./confirm-dialog.component.scss"],
})
export class ConfirmDialogComponent implements OnInit {
    constructor(
        @Inject(MAT_DIALOG_DATA) public data: any,
        private matDialogRef: MatDialogRef<ConfirmDialogComponent>
    ) {}

    ngOnInit(): void {}

    /**
     * 確認ダイアログを表示
     */
    confirm(result: boolean): void {
        this.matDialogRef.close(result);
    }
}

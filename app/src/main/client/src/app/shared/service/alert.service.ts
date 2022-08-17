import { Injectable } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { MatSnackBar } from "@angular/material/snack-bar";
import { SnackBarComponent } from "@shared/component/snack-bar/snack-bar.component";

@Injectable({
    providedIn: "root",
})
export class AlertService {
    constructor(private matDialog: MatDialog, private snackBar: MatSnackBar) {}

    /**
     * SUCCESSスナックバーを表示
     *
     * @param message メッセージ
     */
    public success(message: string): void {
        this.snackBar.openFromComponent(SnackBarComponent, {
            duration: 5000,
            data: { message: message, level: "SUCCESS" },
        });
    }

    /**
     * INFOスナックバーを表示
     *
     * @param message メッセージ
     */
    public info(message: string): void {
        this.snackBar.openFromComponent(SnackBarComponent, {
            duration: 5000,
            data: { message: message, level: "INFO" },
        });
    }

    /**
     * WARNスナックバーを表示
     *
     * @param message メッセージ
     */
    public warn(message: string): void {
        this.snackBar.openFromComponent(SnackBarComponent, {
            duration: 5000,
            data: { message: message, level: "WARN" },
        });
    }

    /**
     * ERRORスナックバーを表示
     *
     * @param message メッセージ
     */
    public error(message: string): void {
        this.snackBar.openFromComponent(SnackBarComponent, {
            duration: 10000,
            data: { message: message, level: "ERROR" },
        });
    }
}

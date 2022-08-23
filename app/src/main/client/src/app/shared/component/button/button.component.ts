import { Component, Input, OnInit } from "@angular/core";

@Component({
    selector: "app-button",
    templateUrl: "./button.component.html",
    styleUrls: ["./button.component.scss"],
})
export class ButtonComponent implements OnInit {
    /**
     * アイコン
     */
    @Input() icon: string | undefined = undefined;

    /**
     * ラベル
     */
    @Input() label: string = "";

    /**
     * ボタン幅
     */
    @Input() width: string = "110px";

    /**
     * ボタンタイプ
     */
    @Input() type: "submit" | "button" | "reset" = "button";

    /**
     * ボタンの有効性
     */
    @Input() disabled: boolean = false;

    /**
     * 色を反転するか
     */
    @Input() reverseColor: boolean = false;

    /**
     * ボタンの背景色
     */
    @Input() color: "primary" | "accent" | "warn" = "primary";

    constructor() {}

    ngOnInit(): void {}
}

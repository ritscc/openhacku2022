import { Component, OnInit } from "@angular/core";
import { NavigationEnd, Router } from "@angular/router";
import { filter } from "rxjs/operators";

@Component({
    selector: "app-header",
    templateUrl: "./header.component.html",
    styleUrls: ["./header.component.scss"],
})
export class HeaderComponent implements OnInit {
    /**
     * サイドナビを有効化するかどうか
     */
    isSidenavValid: boolean = false;

    constructor(private router: Router) {
        // ナビゲーション終了時にサイドナビの状態を定義
        this.router.events
            .pipe(filter((e) => e instanceof NavigationEnd))
            .subscribe(() => this.setIsSidenavValid());
    }

    ngOnInit(): void {}

    /**
     * サイドナビの状態をセットする
     */
    setIsSidenavValid(): void {
        this.isSidenavValid = true;
        // ホームルートにいる場合、サイドナビを無効化
        if (this.router.url === "/") {
            this.isSidenavValid = false;
        }
    }
}

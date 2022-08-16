import { Component, OnInit } from "@angular/core";

// TODO: APIが実装完了したら削除
type UserResponse = {
    id: number;
    iconUrl: string | null;
};

@Component({
    selector: "app-user-icon",
    templateUrl: "./user-icon.component.html",
    styleUrls: ["./user-icon.component.scss"],
})
export class UserIconComponent implements OnInit {
    /**
     * ログインユーザ
     */
    loginUser!: UserResponse;

    constructor() {}

    ngOnInit(): void {
        this.loginUser = {
            id: 1,
            iconUrl: null,
        };
    }
}

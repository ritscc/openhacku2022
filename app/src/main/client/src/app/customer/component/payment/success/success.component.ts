import { Component, OnInit } from "@angular/core";
import { AuthService } from "@api/services/auth.service";
import { Router } from "@angular/router";

@Component({
    selector: "app-success",
    templateUrl: "./success.component.html",
    styleUrls: ["./success.component.scss"],
})
export class SuccessComponent implements OnInit {
    constructor(private authService: AuthService, private router: Router) {}

    ngOnInit(): void {
        this.authService.logout({}).subscribe(() => {
            // 5s後にホーム画面に遷移する
            setTimeout(() => {
                this.router.navigate(["/"]);
            }, 5000);
        });
    }
}

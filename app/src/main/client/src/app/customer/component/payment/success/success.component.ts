import { Component, OnInit } from "@angular/core";
import { AuthService } from "@api/services/auth.service";

@Component({
    selector: "app-success",
    templateUrl: "./success.component.html",
    styleUrls: ["./success.component.scss"],
})
export class SuccessComponent implements OnInit {
    constructor(private authService: AuthService) {}

    ngOnInit(): void {
        this.authService.logout({}).subscribe();
    }
}

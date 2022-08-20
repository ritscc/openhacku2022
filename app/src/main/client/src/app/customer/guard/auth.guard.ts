import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { TransactionService } from "@api/services/transaction.service";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";
import { map } from "rxjs/operators";

@UntilDestroy()
@Injectable({
    providedIn: "root",
})
export class AuthGuard implements CanActivate {
    constructor(private transactionService: TransactionService) {}

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        return this.transactionService
            .getLoginTransaction()
            .pipe(untilDestroyed(this))
            .pipe(map(() => true));
    }
}

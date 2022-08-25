import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, NavigationEnd, Router } from "@angular/router";
import { BehaviorSubject, Observable } from "rxjs";
import { filter } from "rxjs/operators";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";

@UntilDestroy()
@Injectable({
    providedIn: "root",
})
export class TitleService {
    /**
     * タイトル
     */
    title = new BehaviorSubject<string>("");

    constructor(private router: Router) {
        this.router.events
            .pipe(untilDestroyed(this))
            .pipe(filter((event) => event instanceof NavigationEnd))
            .subscribe((_) => {
                const root = this.router.routerState.snapshot.root;

                this.searchTitle(root, []);
            });
    }

    /**
     * タイトルを取得
     */
    getTitle(): Observable<string> {
        return this.title.asObservable();
    }

    /**
     * タイトル検索
     */
    private searchTitle(route: ActivatedRouteSnapshot, parentUrl: string[]) {
        if (route) {
            const routeUrl = parentUrl.concat(route.url.map((url) => url.path));

            if (route.firstChild) {
                this.searchTitle(route.firstChild, routeUrl);
            } else {
                this.title.next(route.data["title"]);
            }
        }

        return "";
    }
}

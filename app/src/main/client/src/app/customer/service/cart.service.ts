import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { OrderMenuRequest } from "@api/models/order-menu-request";
import { map } from "rxjs/operators";

@Injectable({
    providedIn: "root",
})
export class CartService {
    private MENUS_KEY = "MENUS";

    constructor() {}

    /**
     * カートに入ったメニューリストを取得
     */
    getMenus(): Observable<OrderMenuRequest[]> {
        return of(JSON.parse(localStorage.getItem(this.MENUS_KEY) ?? "[]"));
    }

    /**
     * カートにメニューリストを追加
     */
    addMenus(menus: OrderMenuRequest[]): Observable<void> {
        return this.getMenus().pipe(
            map((localStorageMenus) => {
                for (let i = 0; i < menus.length; i++) {
                    const existingMenu = localStorageMenus.filter(
                        (element) => element.menuId === menus[i].menuId
                    )[0];
                    if (existingMenu) {
                        localStorageMenus[localStorageMenus.indexOf(existingMenu)].quantity +=
                            menus[i].quantity;
                    } else {
                        localStorageMenus.push({
                            menuId: menus[i].menuId,
                            quantity: menus[i].quantity,
                        });
                    }
                }

                // ローカルストレージにメニューを追加
                localStorage.setItem(this.MENUS_KEY, JSON.stringify(localStorageMenus));
            })
        );
    }
}

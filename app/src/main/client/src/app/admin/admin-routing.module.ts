import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
// components
import { LoginComponent } from "@admin/component/login/login.component";
import { OrdersComponent } from "@admin/component/orders/orders.component";
import { MenusComponent } from "@admin/component/menus/menus.component";
import { MenuCreateFormComponent } from "@admin/component/menu-create-form/menu-create-form.component";

const routes: Routes = [
    { path: "login", component: LoginComponent, data: { title: "店舗ログイン" } },
    { path: "orders", component: OrdersComponent, data: { title: "注文管理" } },
    {
        path: "menus",
        children: [
            {
                path: "",
                component: MenusComponent,
                data: { title: "メニュー管理" },
            },
            {
                path: "new",
                component: MenuCreateFormComponent,
                data: { title: "メニュー登録" },
            },
            { path: "**", redirectTo: "menus", pathMatch: "full" },
        ],
    },
    { path: "**", redirectTo: "login", pathMatch: "full" },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class AdminRoutingModule {}

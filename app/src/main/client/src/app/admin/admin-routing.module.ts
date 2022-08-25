import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

// components
import { LoginComponent } from "@admin/component/login/login.component";
import { MenusComponent } from "@admin/component/menus/menus.component";
import { MenuCreateFormComponent } from "@admin/component/menu-create-form/menu-create-form.component";
import { ShopComponent } from "@admin/component/shop/shop.component";

const routes: Routes = [
    { path: "login", component: LoginComponent, data: { title: "店舗ログイン" } },
    {
        path: "shop",
        component: ShopComponent,
        data: { title: "店舗情報" },
    },
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
        ],
    },
    { path: "**", redirectTo: "shop", pathMatch: "full" },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class AdminRoutingModule {}

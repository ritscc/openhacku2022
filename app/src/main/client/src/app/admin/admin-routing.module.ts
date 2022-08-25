import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
// components
import { LoginComponent } from "@admin/component/login/login.component";
import { OrdersComponent } from "@admin/component/orders/orders.component";
import { MenusComponent } from "@admin/component/menus/menus.component";
import { MenuCreateFormComponent } from "@admin/component/menu-create-form/menu-create-form.component";

const routes: Routes = [
    { path: "login", component: LoginComponent },
    { path: "orders", component: OrdersComponent },
    { path: "**", redirectTo: "login", pathMatch: "full" },
    {
        path: "menus",
        children: [
            {
                path: "",
                component: MenusComponent,
            },
            {
                path: "new",
                component: MenuCreateFormComponent,
            },
        ],
    },
    { path: "**", redirectTo: "menus", pathMatch: "full" },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class AdminRoutingModule {}

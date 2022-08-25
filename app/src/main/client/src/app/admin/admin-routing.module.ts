import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "@admin/component/login/login.component";
import { OrdersComponent } from "@admin/component/orders/orders.component";

// components

const routes: Routes = [
    { path: "login", component: LoginComponent },
    { path: "orders", component: OrdersComponent },
    { path: "**", redirectTo: "login", pathMatch: "full" },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class AdminRoutingModule {}

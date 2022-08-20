import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

// components
import { HomeComponent } from "@customer/component/home/home.component";
import { OrderComponent } from "@customer/component/order/order.component";
import { PaymentComponent } from "@customer/component/payment/payment.component";
import { AuthGuard } from "@customer/guard/auth.guard";

const routes: Routes = [
    {
        path: "",
        component: HomeComponent,
    },
    {
        path: "dashboard",
        canActivate: [AuthGuard],
        children: [
            {
                path: "",
                component: OrderComponent,
            },
            {
                path: "payment",
                component: PaymentComponent,
            },
        ],
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class CustomerRoutingModule {}

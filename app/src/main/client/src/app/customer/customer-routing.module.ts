import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

// components
import { HomeComponent } from "@customer/component/home/home.component";
import { OrderComponent } from "@customer/component/order/order.component";
import { PaymentComponent } from "@customer/component/payment/payment.component";
import { SuccessComponent } from "@customer/component/payment/success/success.component";

const routes: Routes = [
    {
        path: "",
        component: HomeComponent,
        data: { title: "" },
    },
    {
        path: "dashboard",
        children: [
            {
                path: "",
                component: OrderComponent,
                data: { title: "注文画面" },
            },
            {
                path: "payment",
                children: [
                    {
                        path: "",
                        component: PaymentComponent,
                        data: { title: "お会計" },
                    },
                    {
                        path: "success",
                        component: SuccessComponent,
                        data: { title: "お会計" },
                    },
                ],
            },
        ],
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class CustomerRoutingModule {}

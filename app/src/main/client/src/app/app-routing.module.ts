import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

// components
import { HomeComponent } from "@app/component/home/home.component";
import { PaymentWithQrComponent } from "@app/component/payment-with-qr/payment-with-qr.component";
import { PageContainerComponent } from "@app/component/page-container/page-container.component";
import { OrderComponent } from "@app/component/order/order.component";

const routes: Routes = [
    {
        path: "",
        component: PageContainerComponent,
        children: [
            {
                path: "",
                component: HomeComponent,
            },
            {
                path: "order",
                component: OrderComponent,
            },
            {
                path: "payment/qr",
                component: PaymentWithQrComponent,
            },
        ],
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}

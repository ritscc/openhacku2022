import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

// components
import { HomeComponent } from "@app/component/home/home.component";
import { PaymentWithQrComponent } from "@app/component/payment-with-qr/payment-with-qr.component";
import { PageContainerComponent } from "@app/component/page-container/page-container.component";
import { PaymentComponent } from "@app/component/payment/payment.component";

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
                path: "payment/qr",
                component: PaymentWithQrComponent,
            },
            {
                path: "payment",
                component: PaymentComponent,
            },
        ],
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}

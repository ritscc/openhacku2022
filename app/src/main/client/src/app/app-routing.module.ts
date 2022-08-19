import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

// components
import { HomeComponent } from "@app/component/home/home.component";
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

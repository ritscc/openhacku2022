import { NgModule } from "@angular/core";

// // modules
import { SharedModule } from "@shared/shared.module";

// components
import { HomeComponent } from "@customer/component/home/home.component";
import { PaymentWithQrComponent } from "@customer/component/payment-with-qr/payment-with-qr.component";
import { QrDialogComponent } from "@customer/component/qr-dialog/qr-dialog.component";
import { PaymentComponent } from "@customer/component/payment/payment.component";
import { OrderComponent } from "@customer/component/order/order.component";
import { MenusComponent } from "@customer/component/order/menus/menus.component";

@NgModule({
    declarations: [
        HomeComponent,
        PaymentWithQrComponent,
        QrDialogComponent,
        PaymentComponent,
        OrderComponent,
        MenusComponent,
    ],
    imports: [SharedModule],
})
export class CustomerModule {}

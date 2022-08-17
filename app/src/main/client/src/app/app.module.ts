import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

// modules
import { SharedModule } from "@shared/shared.module";
import { AppRoutingModule } from "@app/app-routing.module";

// components
import { AppComponent } from "@app/app.component";
import { PageContainerComponent } from "./component/page-container/page-container.component";
import { HomeComponent } from "./component/home/home.component";
import { PaymentWithQrComponent } from "./component/payment-with-qr/payment-with-qr.component";
import { OrderComponent } from "@app/component/order/order.component";
import { MenusComponent } from "@app/component/order/menus/menus.component";

@NgModule({
    declarations: [
        AppComponent,
        PageContainerComponent,
        HomeComponent,
        PaymentWithQrComponent,
        OrderComponent,
        MenusComponent,
    ],
    imports: [SharedModule, AppRoutingModule, BrowserModule, BrowserAnimationsModule],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule {}

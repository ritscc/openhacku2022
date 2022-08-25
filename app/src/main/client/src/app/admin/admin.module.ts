import { NgModule } from "@angular/core";

// // modules
import { SharedModule } from "@shared/shared.module";
import { LoginComponent } from "./component/login/login.component";
import { OrdersComponent } from "./component/orders/orders.component";

@NgModule({
    declarations: [LoginComponent, OrdersComponent],
    imports: [SharedModule],
})
export class AdminModule {}

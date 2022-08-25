import { NgModule } from "@angular/core";

// // modules
import { SharedModule } from "@shared/shared.module";
import { LoginComponent } from "./component/login/login.component";
import { OrdersComponent } from "./component/orders/orders.component";
import { MenusComponent } from "./component/menus/menus.component";
import { MenuCreateFormComponent } from "./component/menu-create-form/menu-create-form.component";

@NgModule({
    declarations: [LoginComponent, MenusComponent, MenuCreateFormComponent, OrdersComponent],
    imports: [SharedModule],
})
export class AdminModule {}

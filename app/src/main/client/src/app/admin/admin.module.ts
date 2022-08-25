import { NgModule } from "@angular/core";

// // modules
import { SharedModule } from "@shared/shared.module";
import { LoginComponent } from "./component/login/login.component";
import { MenusComponent } from "./component/menus/menus.component";
import { MenuCreateFormComponent } from "./component/menu-create-form/menu-create-form.component";
import { ShopComponent } from "./component/shop/shop.component";

@NgModule({
    declarations: [LoginComponent, MenusComponent, MenuCreateFormComponent, ShopComponent],
    imports: [SharedModule],
})
export class AdminModule {}

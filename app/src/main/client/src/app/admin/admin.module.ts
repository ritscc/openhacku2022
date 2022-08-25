import { NgModule } from "@angular/core";

// // modules
import { SharedModule } from "@shared/shared.module";
import { LoginComponent } from "./component/login/login.component";
import { MenusComponent } from "./component/menus/menus.component";
import { MenuCreateFormComponent } from "./component/menu-create-form/menu-create-form.component";

@NgModule({
    declarations: [LoginComponent, MenusComponent, MenuCreateFormComponent],
    imports: [SharedModule],
})
export class AdminModule {}

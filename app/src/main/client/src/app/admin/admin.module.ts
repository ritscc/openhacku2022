import { NgModule } from "@angular/core";

// // modules
import { SharedModule } from "@shared/shared.module";
import { LoginComponent } from "./component/login/login.component";
import { MenusComponent } from "./component/menus/menus.component";

@NgModule({
    declarations: [LoginComponent, MenusComponent],
    imports: [SharedModule],
})
export class AdminModule {}

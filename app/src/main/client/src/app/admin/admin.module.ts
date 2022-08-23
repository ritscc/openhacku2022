import { NgModule } from "@angular/core";

// // modules
import { SharedModule } from "@shared/shared.module";
import { LoginComponent } from "./component/login/login.component";

@NgModule({
    declarations: [LoginComponent],
    imports: [SharedModule],
})
export class AdminModule {}

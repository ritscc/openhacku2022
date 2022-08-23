import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "@admin/component/login/login.component";

// components

const routes: Routes = [
    { path: "login", component: LoginComponent },
    // {path: "dashboard", component: LoginComponent},
    { path: "**", redirectTo: "login", pathMatch: "full" },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class AdminRoutingModule {}

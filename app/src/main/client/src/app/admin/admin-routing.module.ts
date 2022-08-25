import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

// components
import { LoginComponent } from "@admin/component/login/login.component";
import { MenusComponent } from "@admin/component/menus/menus.component";

const routes: Routes = [
    { path: "login", component: LoginComponent },
    {
        path: "menus",
        component: MenusComponent,
    },
    { path: "**", redirectTo: "menus", pathMatch: "full" },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class AdminRoutingModule {}

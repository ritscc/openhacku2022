import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

// components
import { PageContainerComponent } from "@shared/component/page-container/page-container.component";

const routes: Routes = [
    {
        path: "",
        component: PageContainerComponent,
        children: [
            {
                path: "",
                loadChildren: () =>
                    import("@customer/customer-routing.module").then(
                        (m) => m.CustomerRoutingModule
                    ),
            },
            {
                path: "admin",
                loadChildren: () =>
                    import("@admin/admin-routing.module").then((m) => m.AdminRoutingModule),
            },
        ],
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}

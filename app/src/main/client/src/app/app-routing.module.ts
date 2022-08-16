import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { PageContainerComponent } from "./component/page-container/page-container.component";

const routes: Routes = [{ path: "", component: PageContainerComponent }];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}

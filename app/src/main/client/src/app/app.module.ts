import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

// components
import { AppComponent } from "@app/app.component";

// modules
import { SharedModule } from "@shared/shared.module";
import { AppRoutingModule } from "@app/app-routing.module";
import { PageContainerComponent } from "./component/page-container/page-container.component";

@NgModule({
    declarations: [AppComponent, PageContainerComponent],
    imports: [SharedModule, AppRoutingModule, BrowserModule, BrowserAnimationsModule],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule {}

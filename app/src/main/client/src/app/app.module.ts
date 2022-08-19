import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

// components
import { AppComponent } from "@app/app.component";

// modules
import { SharedModule } from "@shared/shared.module";
import { AppRoutingModule } from "@app/app-routing.module";
import { CustomerModule } from "@customer/customer.module";
import { AdminModule } from "@admin/admin.module";

@NgModule({
    declarations: [AppComponent],
    imports: [
        SharedModule,
        CustomerModule,
        AdminModule,
        AppRoutingModule,
        BrowserModule,
        BrowserAnimationsModule,
    ],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule {}

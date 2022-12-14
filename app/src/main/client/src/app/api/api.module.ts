/* tslint:disable */
/* eslint-disable */
import { NgModule, ModuleWithProviders, SkipSelf, Optional } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { ApiConfiguration, ApiConfigurationParams } from "./api-configuration";

import { AdminOrderService } from "./services/admin-order.service";
import { AdminMenuService } from "./services/admin-menu.service";
import { OrderService } from "./services/order.service";
import { AuthService } from "./services/auth.service";
import { BatchService } from "./services/batch.service";
import { AdminAuthService } from "./services/admin-auth.service";
import { TransactionService } from "./services/transaction.service";
import { MenuService } from "./services/menu.service";
import { HealthCheckService } from "./services/health-check.service";
import { CheckoutService } from "./services/checkout.service";
import { AdminTransactionService } from "./services/admin-transaction.service";
import { AdminShopService } from "./services/admin-shop.service";

/**
 * Module that provides all services and configuration.
 */
@NgModule({
    imports: [],
    exports: [],
    declarations: [],
    providers: [
        AdminOrderService,
        AdminMenuService,
        OrderService,
        AuthService,
        BatchService,
        AdminAuthService,
        TransactionService,
        MenuService,
        HealthCheckService,
        CheckoutService,
        AdminTransactionService,
        AdminShopService,
        ApiConfiguration,
    ],
})
export class ApiModule {
    static forRoot(params: ApiConfigurationParams): ModuleWithProviders<ApiModule> {
        return {
            ngModule: ApiModule,
            providers: [
                {
                    provide: ApiConfiguration,
                    useValue: params,
                },
            ],
        };
    }

    constructor(@Optional() @SkipSelf() parentModule: ApiModule, @Optional() http: HttpClient) {
        if (parentModule) {
            throw new Error("ApiModule is already loaded. Import in your base AppModule only.");
        }
        if (!http) {
            throw new Error(
                "You need to import the HttpClientModule in your AppModule! \n" +
                    "See also https://github.com/angular/angular/issues/20575"
            );
        }
    }
}

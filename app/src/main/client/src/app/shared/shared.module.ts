import { NgModule } from "@angular/core";
import { CommonModule, DatePipe } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterTestingModule } from "@angular/router/testing";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { ApiConfiguration } from "@api/api-configuration";

// UI modules
import { FlexLayoutModule } from "@angular/flex-layout";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { MatButtonToggleModule } from "@angular/material/button-toggle";
import { MatSlideToggleModule } from "@angular/material/slide-toggle";
import { MatMenuModule } from "@angular/material/menu";
import { MatListModule } from "@angular/material/list";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatSelectModule } from "@angular/material/select";
import { MatExpansionModule } from "@angular/material/expansion";
import { MatCardModule } from "@angular/material/card";
import { MatInputModule } from "@angular/material/input";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatTableModule } from "@angular/material/table";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatSortModule } from "@angular/material/sort";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { MatDialogModule } from "@angular/material/dialog";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { MatChipsModule } from "@angular/material/chips";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MAT_DATE_LOCALE, MatNativeDateModule } from "@angular/material/core";
import { MatAutocompleteModule } from "@angular/material/autocomplete";
import { DragDropModule } from "@angular/cdk/drag-drop";
import { TextFieldModule } from "@angular/cdk/text-field";
import { MatTabsModule } from "@angular/material/tabs";
import { ZXingScannerModule } from "@zxing/ngx-scanner";
import { MatStepperModule } from "@angular/material/stepper";
import { QRCodeModule } from "angularx-qrcode";

// pipes
import { NumberPipe } from "@shared/pipe/number.pipe";

// interceptors
import { ErrorResponseInterceptor } from "@shared/interceptor/error-response.interceptor";
import { CredentialsInterceptor } from "@shared/interceptor/credentials.interceptor";
import { LoadingIndicatorInterceptor } from "@shared/interceptor/loading-indicator.interceptor";

// components
import { HeaderComponent } from "@shared/component/header/header.component";
import { UserIconComponent } from "@shared/component/user-icon/user-icon.component";
import { SnackBarComponent } from "@shared/component/snack-bar/snack-bar.component";
import { QrLoaderDialogComponent } from "@shared/component/qr-loader-dialog/qr-loader-dialog.component";
import { ButtonComponent } from "@shared/component/button/button.component";
import { FooterComponent } from "@shared/component/footer/footer.component";
import { PageContainerComponent } from "@shared/component/page-container/page-container.component";
import { ProgressSpinnerComponent } from "@shared/component/progress-spinner/progress-spinner.component";
import { SharedInputComponent } from "./component/shared-input/shared-input.component";

@NgModule({
    declarations: [
        HeaderComponent,
        UserIconComponent,
        SnackBarComponent,
        QrLoaderDialogComponent,
        NumberPipe,
        ButtonComponent,
        FooterComponent,
        PageContainerComponent,
        ProgressSpinnerComponent,
        SharedInputComponent,
    ],
    imports: [
        // modules
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        RouterTestingModule,
        HttpClientModule,

        // UI modules
        FlexLayoutModule,
        MatIconModule,
        MatButtonModule,
        MatButtonToggleModule,
        MatSlideToggleModule,
        MatMenuModule,
        MatListModule,
        MatSidenavModule,
        MatToolbarModule,
        MatSelectModule,
        MatExpansionModule,
        MatCardModule,
        MatInputModule,
        MatFormFieldModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        MatSnackBarModule,
        MatDialogModule,
        MatProgressSpinnerModule,
        MatCheckboxModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatAutocompleteModule,
        DragDropModule,
        MatChipsModule,
        TextFieldModule,
        MatTabsModule,
        ZXingScannerModule,
        MatStepperModule,
        QRCodeModule,
    ],
    exports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        RouterTestingModule,

        // UI modules
        FlexLayoutModule,
        MatIconModule,
        MatButtonModule,
        MatButtonToggleModule,
        MatSlideToggleModule,
        MatMenuModule,
        MatListModule,
        MatSidenavModule,
        MatToolbarModule,
        MatSelectModule,
        MatExpansionModule,
        MatCardModule,
        MatInputModule,
        MatFormFieldModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        MatSnackBarModule,
        MatDialogModule,
        MatProgressSpinnerModule,
        MatCheckboxModule,
        MatChipsModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatAutocompleteModule,
        DragDropModule,
        TextFieldModule,
        MatTabsModule,
        MatStepperModule,
        QRCodeModule,

        // pipes
        NumberPipe,

        // components
        HeaderComponent,
        ButtonComponent,
        FooterComponent,
        PageContainerComponent,
        SharedInputComponent,
    ],
    providers: [
        { provide: ApiConfiguration, useValue: { rootUrl: environment.API_ROOT_URL } },
        { provide: MAT_DATE_LOCALE, useValue: "ja-JP" },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorResponseInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: CredentialsInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: LoadingIndicatorInterceptor, multi: true },
        DatePipe,
    ],
})
export class SharedModule {}

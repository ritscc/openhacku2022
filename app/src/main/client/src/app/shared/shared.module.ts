import { NgModule } from "@angular/core";
import { CommonModule, DatePipe } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterTestingModule } from "@angular/router/testing";
import { HttpClientModule } from "@angular/common/http";
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

// components
import { HeaderComponent } from "./component/header/header.component";
import { UserIconComponent } from "./component/user-icon/user-icon.component";
import { SnackBarComponent } from "./component/snack-bar/snack-bar.component";
import { QrLoaderDialogComponent } from "./component/qr-loader-dialog/qr-loader-dialog.component";

// other imports
import { QRCodeModule } from "angularx-qrcode";

@NgModule({
    declarations: [HeaderComponent, UserIconComponent, SnackBarComponent, QrLoaderDialogComponent],
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

        // other modules
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

        // components
        HeaderComponent,

        // other modules
        QRCodeModule,
    ],
    providers: [
        { provide: ApiConfiguration, useValue: { rootUrl: environment.API_ROOT_URL } },
        { provide: MAT_DATE_LOCALE, useValue: "ja-JP" },
        DatePipe,
    ],
})
export class SharedModule {}

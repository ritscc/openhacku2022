import { ComponentFixture, TestBed } from "@angular/core/testing";

import { TableNumberDialogComponent } from "./table-number-dialog.component";

describe("TableNumberDialogComponent", () => {
    let component: TableNumberDialogComponent;
    let fixture: ComponentFixture<TableNumberDialogComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [TableNumberDialogComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(TableNumberDialogComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});

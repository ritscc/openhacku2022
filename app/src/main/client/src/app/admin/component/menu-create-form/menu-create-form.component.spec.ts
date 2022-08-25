import { ComponentFixture, TestBed } from "@angular/core/testing";

import { MenuCreateFormComponent } from "./menu-create-form.component";

describe("MenuCreateFormComponent", () => {
    let component: MenuCreateFormComponent;
    let fixture: ComponentFixture<MenuCreateFormComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [MenuCreateFormComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(MenuCreateFormComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});

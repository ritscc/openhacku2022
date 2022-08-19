import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SharedInputComponent } from "./shared-input.component";

describe("SharedInputComponent", () => {
    let component: SharedInputComponent;
    let fixture: ComponentFixture<SharedInputComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [SharedInputComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(SharedInputComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});

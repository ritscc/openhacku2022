import { ComponentFixture, TestBed } from "@angular/core/testing";

import { PaymentWithQrComponent } from "./payment-with-qr.component";

describe("PaymentWithQrComponent", () => {
    let component: PaymentWithQrComponent;
    let fixture: ComponentFixture<PaymentWithQrComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [PaymentWithQrComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(PaymentWithQrComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});

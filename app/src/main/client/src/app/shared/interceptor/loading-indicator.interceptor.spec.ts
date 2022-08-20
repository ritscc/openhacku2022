import { TestBed } from "@angular/core/testing";

import { LoadingIndicatorInterceptor } from "./loading-indicator.interceptor";

describe("LoadingIndicatorInterceptor", () => {
    beforeEach(() =>
        TestBed.configureTestingModule({
            providers: [LoadingIndicatorInterceptor],
        })
    );

    it("should be created", () => {
        const interceptor: LoadingIndicatorInterceptor = TestBed.inject(
            LoadingIndicatorInterceptor
        );
        expect(interceptor).toBeTruthy();
    });
});

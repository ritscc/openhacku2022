package cc.rits.openhacku2022.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cc.rits.openhacku2022.api.response.CheckoutResponse;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.service.TransactionService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 決済コントローラ
 */
@Tag(name = "Checkout", description = "支払い")
@RestController
@RequestMapping(path = "/api/checkout", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class CheckoutRestController {

    private final TransactionService transactionService;

    /**
     * 決済API
     *
     * @param transaction 取引
     * @return 決済レスポンス
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CheckoutResponse checkout( //
        @Parameter(hidden = true) final TransactionModel transaction //
    ) {
        final var redirectUrl = this.transactionService.getCheckoutUrl(transaction);
        return CheckoutResponse.builder() //
            .redirectUrl(redirectUrl) //
            .build();
    }

}

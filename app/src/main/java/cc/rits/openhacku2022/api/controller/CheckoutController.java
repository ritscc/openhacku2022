package cc.rits.openhacku2022.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.service.TransactionService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 取引コントローラ
 */
@Tag(name = "Checkout", description = "支払い")
@RestController
@RequestMapping(path = "/api/checkout", produces = MediaType.TEXT_PLAIN_VALUE)
@Validated
@RequiredArgsConstructor
public class CheckoutController {

    private final TransactionService transactionService;

    /**
     * 支払いAPI
     *
     * @param transaction 取引
     * @return リダイレクト先
     */
    @PostMapping
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String checkout( //
        @Parameter(hidden = true) final TransactionModel transaction //
    ) {
        return this.transactionService.getCheckoutUrl(transaction);
    }

}

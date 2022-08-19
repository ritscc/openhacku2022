package cc.rits.openhacku2022.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cc.rits.openhacku2022.api.response.TransactionResponse;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.service.TransactionService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 取引コントローラ
 */
@Tag(name = "Transaction", description = "取引")
@RestController
@RequestMapping(path = "/api/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class TransactionRestController {

    private final TransactionService transactionService;

    /**
     * ログイン取引取得API
     *
     * @param transaction 取引
     * @return 取引
     */
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse getLoginTransaction( //
        @Parameter(hidden = true) final TransactionModel transaction //
    ) {
        return new TransactionResponse(this.transactionService.getTransaction(transaction));
    }

}

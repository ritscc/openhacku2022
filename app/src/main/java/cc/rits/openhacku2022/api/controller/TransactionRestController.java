package cc.rits.openhacku2022.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cc.rits.openhacku2022.api.response.TransactionResponse;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.query_service.TransactionQueryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 取引コントローラ
 */
@Tag(name = "Transaction", description = "取引")
@RestController
@RequestMapping(path = "/api/shop/{shop_id}/transactions/me", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class TransactionRestController {

    private final TransactionQueryService transactionQueryService;

    /**
     * 取引取得API
     *
     * @param shopId 店舗ID
     * @param transaction 取引
     * @return 取引
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse getTransaction( //
        @PathVariable("shop_id") Integer shopId, //
        @Parameter(hidden = true) final TransactionModel transaction //
    ) {
        // 取引取得
        return this.transactionQueryService.getTransaction(shopId, transaction);
    }

}

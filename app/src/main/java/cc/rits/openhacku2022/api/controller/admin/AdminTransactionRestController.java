package cc.rits.openhacku2022.api.controller.admin;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cc.rits.openhacku2022.api.response.TransactionResponse;
import cc.rits.openhacku2022.api.response.TransactionsResponse;
import cc.rits.openhacku2022.model.ShopModel;
import cc.rits.openhacku2022.service.admin.AdminTransactionService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 取引コントローラ(管理者用)
 */
@Tag(name = "Admin Transaction", description = "取引")
@RestController
@RequestMapping(path = "/api/admin/shops/{shop_id}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class AdminTransactionRestController {

    private final AdminTransactionService adminTransactionService;

    /**
     * 取引リスト取得API
     * 
     * @param shopId 店舗ID
     * @param shop 店舗
     * @return 取引リスト
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TransactionsResponse getTransactions( //
        @PathVariable("shop_id") final Integer shopId, //
        @Parameter(hidden = true) final ShopModel shop //
    ) {
        final var transactions = this.adminTransactionService.getTransactions(shopId, shop).stream() //
            .map(TransactionResponse::new) //
            .collect(Collectors.toList());

        return new TransactionsResponse(transactions);
    }

    /**
     * 取引取得API
     *
     * @param shopId 店舗ID
     * @param transactionId 取引ID
     * @param shop 店舗
     * @return 取引
     */
    @GetMapping("/{transaction_id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse getTransaction( //
        @PathVariable("shop_id") final Integer shopId, //
        @PathVariable("transaction_id") final Integer transactionId, //
        @Parameter(hidden = true) final ShopModel shop //
    ) {
        // 取引取得
        return new TransactionResponse(this.adminTransactionService.getTransaction(shopId, transactionId, shop));
    }

    /**
     * 取引削除API
     * 
     * @param shopId 店舗ID
     * @param transactionId 取引ID
     * @param shop 店舗
     */
    @DeleteMapping("/{transaction_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTransaction( //
        @PathVariable("shop_id") final Integer shopId, //
        @PathVariable("transaction_id") final Integer transactionId, //
        @Parameter(hidden = true) final ShopModel shop //
    ) {
        this.adminTransactionService.deleteTransaction(shopId, transactionId, shop);
    }

    /**
     * 全取引削除API
     * 
     * @param shopId 店舗ID
     * @param shop 店舗
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteTransactions( //
        @PathVariable("shop_id") final Integer shopId, //
        @Parameter(hidden = true) final ShopModel shop //
    ) {
        this.adminTransactionService.deleteTransactions(shopId, shop);
    }

}

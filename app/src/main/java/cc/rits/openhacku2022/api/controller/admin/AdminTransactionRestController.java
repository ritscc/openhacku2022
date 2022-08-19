package cc.rits.openhacku2022.api.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cc.rits.openhacku2022.api.response.TransactionResponse;
import cc.rits.openhacku2022.model.ShopModel;
import cc.rits.openhacku2022.service.AdminTransactionService;
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
        @PathVariable("shop_id") Integer shopId, //
        @PathVariable("transaction_id") Integer transactionId, //
        @Parameter(hidden = true) final ShopModel shop //
    ) {
        // 取引取得
        return new TransactionResponse(this.adminTransactionService.getTransaction(shopId, transactionId, shop));
    }

}

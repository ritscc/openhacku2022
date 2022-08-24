package cc.rits.openhacku2022.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cc.rits.openhacku2022.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * バッチコントローラ
 */
@Tag(name = "Batch", description = "バッチ")
@RestController
@RequestMapping(path = "/api/batch")
@Validated
@RequiredArgsConstructor
public class BatchRestController {

    private final TransactionService transactionService;

    /**
     * 期限切れ取引削除API
     */
    @PostMapping("/delete_expired_transactions")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExpiredTransaction() {
        this.transactionService.deleteExpiredTransaction();
    }

}

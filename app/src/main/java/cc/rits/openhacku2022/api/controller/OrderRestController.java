package cc.rits.openhacku2022.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cc.rits.openhacku2022.api.request.OrderCreateRequest;
import cc.rits.openhacku2022.api.validation.RequestValidation;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.service.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 注文コントローラ
 */
@Tag(name = "Order", description = "注文")
@RestController
@RequestMapping(path = "/api/shops/{shop_id}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    /**
     * 注文作成API
     *
     * @param shopId 店舗ID
     * @param requestBody 注文作成リクエスト
     * @param transaction 取引
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createOrder( //
        @PathVariable("shop_id") final Integer shopId, //
        @RequestValidation @RequestBody final OrderCreateRequest requestBody, //
        @Parameter(hidden = true) final TransactionModel transaction //
    ) {
        this.orderService.createOrder(shopId, requestBody, transaction);
    }

}

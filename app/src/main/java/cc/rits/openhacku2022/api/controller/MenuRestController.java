package cc.rits.openhacku2022.api.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cc.rits.openhacku2022.api.response.MenuResponse;
import cc.rits.openhacku2022.api.response.MenusResponse;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.service.MenuService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * メニューコントローラ
 */
@Tag(name = "Menu", description = "メニュー")
@RestController
@RequestMapping(path = "/api/shops/{shop_id}/menus", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class MenuRestController {

    private final MenuService menuService;

    /**
     * メニューリスト取得API
     *
     * @param shopId 店舗ID
     * @param transaction 取引
     * @return メニューリスト
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public MenusResponse getMenus( //
        @PathVariable("shop_id") final Integer shopId, //
        @Parameter(hidden = true) final TransactionModel transaction //
    ) {
        final var menus = this.menuService.getMenus(shopId, transaction).stream() //
            .map(MenuResponse::new) //
            .collect(Collectors.toList());
        return new MenusResponse(menus);
    }

}

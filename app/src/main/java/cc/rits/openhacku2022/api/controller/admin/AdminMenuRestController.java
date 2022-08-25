package cc.rits.openhacku2022.api.controller.admin;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cc.rits.openhacku2022.api.request.MenuUpsertRequest;
import cc.rits.openhacku2022.api.response.MenuResponse;
import cc.rits.openhacku2022.api.response.MenusResponse;
import cc.rits.openhacku2022.api.validation.RequestValidation;
import cc.rits.openhacku2022.model.ShopModel;
import cc.rits.openhacku2022.service.admin.AdminMenuService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * メニューコントローラ(管理者用)
 */
@Tag(name = "Admin Menu", description = "メニュー")
@RestController
@RequestMapping(path = "/api/admin/shops/{shop_id}/menus", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class AdminMenuRestController {

    private final AdminMenuService adminMenuService;

    /**
     * メニューリスト取得API
     *
     * @param shopId 店舗ID
     * @param shop 店舗
     * @return メニューリスト
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MenusResponse getMenus( //
        @PathVariable("shop_id") final Integer shopId, //
        @Parameter(hidden = true) final ShopModel shop //
    ) {
        final var menus = this.adminMenuService.getMenus(shopId, shop).stream() //
            .map(MenuResponse::new) //
            .collect(Collectors.toList());
        return new MenusResponse(menus);
    }

    /**
     * メニュー作成API
     *
     * @param shopId 店舗ID
     * @param requestBody メニュー作成リクエスト
     * @param shop 店舗
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createMenu( //
        @PathVariable("shop_id") Integer shopId, //
        @RequestValidation @RequestBody final MenuUpsertRequest requestBody, //
        @Parameter(hidden = true) final ShopModel shop //
    ) {
        this.adminMenuService.createMenu(shopId, requestBody, shop);
    }

    /**
     * メニュー削除API
     * 
     * @param shopId 店舗ID
     * @param menuId メニューID
     * @param shop 店舗
     */
    @DeleteMapping("{menu_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMenu( //
        @PathVariable("shop_id") Integer shopId, //
        @PathVariable("menu_id") Integer menuId, //
        @Parameter(hidden = true) final ShopModel shop //
    ) {
        this.adminMenuService.deleteMenu(shopId, menuId, shop);
    }

    /**
     * メニュー更新API
     * 
     * @param shopId 店舗ID
     * @param menuId メニューID
     * @param requestBody メニュー更新リクエスト
     * @param shop 店舗
     */
    @PutMapping("/{menu_id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMenu( //
        @PathVariable("shop_id") final Integer shopId, //
        @PathVariable("menu_id") final Integer menuId, //
        @RequestValidation @RequestBody final MenuUpsertRequest requestBody, //
        @Parameter(hidden = true) final ShopModel shop //
    ) {
        this.adminMenuService.updateMenu(shopId, menuId, requestBody, shop);
    }

}

package cc.rits.openhacku2022.api.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cc.rits.openhacku2022.api.response.ShopResponse;
import cc.rits.openhacku2022.model.ShopModel;
import cc.rits.openhacku2022.service.admin.AdminShopService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 店舗コントローラ(管理者)
 */
@Tag(name = "Admin Shop", description = "店舗")
@RestController
@RequestMapping(path = "/api/admin/shops", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class AdminShopRestController {

    private final AdminShopService adminShopService;

    /**
     * 店舗取得API(管理者)
     * 
     * @param shop 店舗
     * @return 店舗
     */
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public ShopResponse getShop( //
        @Parameter(hidden = true) final ShopModel shop //
    ) {
        return new ShopResponse(this.adminShopService.getShop(shop));
    }
}

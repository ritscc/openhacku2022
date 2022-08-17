package cc.rits.openhacku2022.api.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cc.rits.openhacku2022.api.request.AdminLoginRequest;
import cc.rits.openhacku2022.service.AdminAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 認証コントローラ(管理者用)
 */
@Tag(name = "Admin Auth", description = "認証")
@RestController
@RequestMapping(path = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class AdminAuthRestController {

    private final AdminAuthService adminAuthService;

    /**
     * ログインAPI
     * 
     * @param requestBody ログインリクエスト
     */
    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public void login( //
        @Validated @RequestBody final AdminLoginRequest requestBody //
    ) {
        this.adminAuthService.login(requestBody);
    }

    /**
     * ログアウトAPI
     */
    @PostMapping("logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout() {
        this.adminAuthService.logout();
    }

}

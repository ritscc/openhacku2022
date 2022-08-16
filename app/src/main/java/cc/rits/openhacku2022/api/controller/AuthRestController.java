package cc.rits.openhacku2022.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cc.rits.openhacku2022.api.request.UserLoginRequest;
import cc.rits.openhacku2022.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 認証コントローラ
 */
@Tag(name = "Auth", description = "認証")
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    /**
     * ログインAPI
     *
     * @param requestBody ログインリクエスト
     */
    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public void login( //
        @Validated @RequestBody final UserLoginRequest requestBody //
    ) {
        this.authService.login(requestBody);
    }

    /**
     * ログアウトAPI
     */
    @PostMapping("logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout() {
        this.authService.logout();
    }

}

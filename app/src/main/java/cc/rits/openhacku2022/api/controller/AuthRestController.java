package cc.rits.openhacku2022.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cc.rits.openhacku2022.api.request.LoginRequest;
import cc.rits.openhacku2022.api.validation.RequestValidation;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.service.AuthService;
import io.swagger.v3.oas.annotations.Parameter;
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
        @RequestValidation @RequestBody final LoginRequest requestBody //
    ) {
        this.authService.login(requestBody);
    }

    /**
     * ログアウトAPI
     *
     * @param transaction 取引
     */
    @PostMapping("logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout( //
        @Parameter(hidden = true) final TransactionModel transaction //
    ) {
        this.authService.logout(transaction);
    }

}

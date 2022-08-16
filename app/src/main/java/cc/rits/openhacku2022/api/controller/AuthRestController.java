package cc.rits.openhacku2022.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cc.rits.openhacku2022.model.TransactionModel;
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
     * ログアウトAPI
     *
     * @param transaction 取引
     */
    @PostMapping("logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout( //
        final TransactionModel transaction //
    ) {
        this.authService.logout(transaction);
    }

}

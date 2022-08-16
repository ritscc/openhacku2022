package cc.rits.openhacku2022.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import cc.rits.openhacku2022.api.request.UserLoginRequest;
import cc.rits.openhacku2022.auth.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;

/**
 * 認証サービス
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomAuthenticationProvider authenticationProvider;

    private final HttpServletRequest httpServletRequest;

    /**
     * ログイン
     *
     * @param requestBody ログインリクエスト
     */
    public void login(final UserLoginRequest requestBody) {
        // 認証を行う
        final var authentication = this.authenticationProvider
            .authenticate(new UsernamePasswordAuthenticationToken(requestBody.getEmail(), requestBody.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // セッションハイジャック対策として、セッションIDを変更
        this.httpServletRequest.changeSessionId();
    }

}

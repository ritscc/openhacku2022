package cc.rits.openhacku2022.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.api.request.AdminLoginRequest;
import cc.rits.openhacku2022.auth.AdminAuthenticationProvider;
import lombok.RequiredArgsConstructor;

/**
 * 認証サービス(管理者用)
 */
@RequiredArgsConstructor
@Service
@Transactional
public class AdminAuthService {

    private final AdminAuthenticationProvider authenticationProvider;

    private final HttpSession httpSession;

    private final HttpServletRequest httpServletRequest;

    /**
     * ログイン
     * 
     * @param requestBody ログインリクエスト
     */
    public void login(final AdminLoginRequest requestBody) {
        final var authentication = this.authenticationProvider
            .authenticate(new UsernamePasswordAuthenticationToken(requestBody.getCode(), requestBody.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        this.httpSession.setMaxInactiveInterval(10800);
        this.httpServletRequest.changeSessionId();
    }

    /**
     * ログアウト
     */
    public void logout() {
        // セッションを無効にする
        this.httpSession.invalidate();
    }

}

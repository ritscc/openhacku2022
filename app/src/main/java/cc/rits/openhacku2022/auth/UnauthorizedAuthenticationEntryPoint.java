package cc.rits.openhacku2022.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;

/**
 * Unauthorized Authentication Entry Point
 */
@RequiredArgsConstructor
@Component
public class UnauthorizedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void commence( //
        final HttpServletRequest request, //
        final HttpServletResponse response, //
        final AuthenticationException authException //
    ) {
        final var exception = new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN);
        this.handlerExceptionResolver.resolveException(request, response, null, exception);
    }

}

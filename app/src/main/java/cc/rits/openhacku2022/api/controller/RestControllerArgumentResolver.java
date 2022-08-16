package cc.rits.openhacku2022.api.controller;

import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME;

import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.UnauthorizedException;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

/**
 * Argument Resolver
 */
@Component
@RequiredArgsConstructor
public class RestControllerArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    private final TransactionRepository transactionRepository;

    @Override
    public boolean supportsParameter(@Nullable final MethodParameter parameter) {
        return Objects.nonNull(parameter) && parameter.getParameterType().equals(TransactionModel.class);
    }

    @Override
    public Object resolveArgument(@Nullable final MethodParameter parameter, @Nullable final ModelAndViewContainer mavContainer,
        @Nullable final NativeWebRequest webRequest, @Nullable final WebDataBinderFactory binderFactory) {
        if (this.supportsParameter(parameter)) {
            final var transactionCode = Optional.ofNullable(this.httpSession.getAttribute(PRINCIPAL_NAME_INDEX_NAME)) //
                .map(Object::toString) //
                .orElseThrow(() -> new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN));
            return this.transactionRepository.selectByCode(transactionCode) //
                .orElseThrow(() -> new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN));
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }

}

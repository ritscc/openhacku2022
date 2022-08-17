package cc.rits.openhacku2022.api.controller;

import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cc.rits.openhacku2022.auth.AdminUserDetailsService;
import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.UnauthorizedException;
import cc.rits.openhacku2022.model.ShopModel;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

/**
 * Argument Resolver
 */
@Component
@RequiredArgsConstructor
public class RestControllerArgumentResolver implements HandlerMethodArgumentResolver {

    private static final List<Object> SUPPORTED_TYPES = List.of(TransactionModel.class, ShopModel.class);

    private final HttpSession httpSession;

    private final TransactionRepository transactionRepository;

    private final AdminUserDetailsService adminUserDetailsService;

    @Override
    public boolean supportsParameter(@Nullable final MethodParameter parameter) {
        return Objects.nonNull(parameter) && SUPPORTED_TYPES.stream().anyMatch(type -> parameter.getParameterType().equals(type));
    }

    @Override
    public Object resolveArgument(@Nullable final MethodParameter parameter, @Nullable final ModelAndViewContainer mavContainer,
        @Nullable final NativeWebRequest webRequest, @Nullable final WebDataBinderFactory binderFactory) {
        if (Objects.nonNull(parameter) && this.supportsParameter(parameter)) {
            if (parameter.getParameterType().equals(TransactionModel.class)) {
                final var transactionCode = Optional.ofNullable(this.httpSession.getAttribute(PRINCIPAL_NAME_INDEX_NAME)) //
                    .map(Object::toString) //
                    .orElseThrow(() -> new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN));
                return this.transactionRepository.selectByCode(transactionCode) //
                    .orElseThrow(() -> new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN));
            } else if (parameter.getParameterType().equals(ShopModel.class)) {
                final var authentication = SecurityContextHolder.getContext().getAuthentication();
                return this.adminUserDetailsService.loadUserByUsername(authentication.getName());
            } else {
                return WebArgumentResolver.UNRESOLVED;
            }
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }

}

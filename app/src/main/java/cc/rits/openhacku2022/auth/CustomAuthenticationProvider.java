package cc.rits.openhacku2022.auth;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

/**
 * Authentication Provider
 */
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    protected void doAfterPropertiesSet() {}

}

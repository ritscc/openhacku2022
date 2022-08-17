package cc.rits.openhacku2022.auth;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

/**
 * Admin Authentication Provider
 */
public class AdminAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    protected void doAfterPropertiesSet() {}

}

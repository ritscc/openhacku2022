package cc.rits.openhacku2022.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import cc.rits.openhacku2022.auth.UnauthorizedAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;

/**
 * セキュリティの設定
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UnauthorizedAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui/**", "/webjars/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CORSを有効化し，CSRFを無効化
        http = http.cors().and().csrf().disable();

        // アクセス許可
        http.authorizeRequests() //
            .antMatchers("/api/admin/login").permitAll() //
            .antMatchers("/api/admin/**").hasRole("ADMIN") //
            .antMatchers("/api/**").permitAll() //
            .anyRequest().authenticated() //
            .and().exceptionHandling().authenticationEntryPoint(this.authenticationEntryPoint);

        return http.build();
    }

}

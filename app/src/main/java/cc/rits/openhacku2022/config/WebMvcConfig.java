package cc.rits.openhacku2022.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cc.rits.openhacku2022.api.controller.RestControllerArgumentResolver;
import lombok.RequiredArgsConstructor;

/**
 * WebMvcの設定
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final RestControllerArgumentResolver argumentResolver;

    @Override
    public void addCorsMappings(final CorsRegistry corsRegistry) {
        corsRegistry //
            .addMapping("/**") //
            .allowedOrigins("localhost:4200") //
            .allowedMethods("*") //
            .allowedHeaders("*") //
            .exposedHeaders("*") //
            .allowCredentials(true) //
            .allowedOriginPatterns("*");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(this.argumentResolver);
    }

}

package cc.rits.openhacku2022.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

/**
 * WebMvcの設定
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

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

}

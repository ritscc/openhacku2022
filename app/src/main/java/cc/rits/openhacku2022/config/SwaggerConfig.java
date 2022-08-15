package cc.rits.openhacku2022.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import cc.rits.openhacku2022.property.ProjectProperty;
import lombok.RequiredArgsConstructor;

/**
 * Swaggerの設定
 */
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    private final ProjectProperty projectProperty;

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder() //
            .group("Public API") //
            .packagesToScan("cc.rits.openhacku2022.api.controller") //
            .build();
    }

    @Bean
    public OpenAPI openAPI() {
        final var info = new Info() //
            .title("Open Hack U 2022 Internal API") //
            .version(this.projectProperty.getVersion());
        return new OpenAPI().info(info);
    }

}

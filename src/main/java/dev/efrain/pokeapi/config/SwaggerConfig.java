package dev.efrain.pokeapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("dev.efrain.pokeapi"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .useDefaultResponseMessages(false);
    }
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Poke API",
                "This API does not intend to be a substitute of the original <a " +
                        "href=\"https://pokeapi.co\">PokeAPI</a> but instead provide further information using that " +
                        "same API.",
                "1.0",
                "",
                new Contact("Efrain Perez","https://efrain.dev","efrain.p93@gmail.com"),
                "MIT",
                "https://github.com/failo/pokeapi/blob/master/LICENSE",
                Collections.emptyList()
        );
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}

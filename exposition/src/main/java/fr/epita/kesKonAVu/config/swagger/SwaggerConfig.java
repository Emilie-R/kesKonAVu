package fr.epita.kesKonAVu.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket customImplementation() {

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("fr.epita.kesKonAVu.exposition")).build()
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)//
                .directModelSubstitute(ZonedDateTime.class, java.util.Date.class) //
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext())) //
                .securitySchemes(Arrays.asList(apiKey()));
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("Swagger kesKonAVu") //
                .description("API du repository kesKonAvu") //
                .license("Apache 2.0") //
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html") //
                .termsOfServiceUrl("") //
                .version("1.0") //
                .contact(new Contact("Some one", "http://localhost:8080", "SomeOne@training.org")) //
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}

package com.ims.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.ims.constant.ExceptionMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        List<ResponseMessage> responseMessages = Arrays.asList(
                new ResponseMessageBuilder()
                        .code(400)
                        .message(ExceptionMessage.INVALID_REQUEST_ERROR_MESSAGE)
                        .build(),
                new ResponseMessageBuilder()
                        .code(500)
                        .message(ExceptionMessage.INTERNAL_SERVER_ERROR_MESSAGE)
                        .build()
        );

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ims.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .securitySchemes(Collections.singletonList(apiKey()))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .globalResponseMessage(RequestMethod.PUT, responseMessages);
    }

    private ApiKey apiKey() {
        return new ApiKey("jwt", "Authorization", "header");
    }
}
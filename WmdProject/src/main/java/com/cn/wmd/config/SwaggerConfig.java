package com.cn.wmd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descripation
 * @Auther wumengda
 * @Date 2019-07-10 9:50
 * @since 1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket createRestApi() {
        List<Parameter> pars = new ArrayList<Parameter>();
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any()).build().globalOperationParameters(pars).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("大家信服 RESTful API").description("我们的征程是星辰大海").termsOfServiceUrl("https://www.cnblogs.com/xiebq/").version("1.0").build();
    }
}

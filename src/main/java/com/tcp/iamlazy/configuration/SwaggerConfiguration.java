package com.tcp.iamlazy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created with intellij IDEA. by 2020 03 15/03/2020 4:43 오전 15 User we at 04 43 To change this
 * template use File | Settings | File Templates.
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Bean
    public Docket authenticationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("인증 요청")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tcp.iamlazy.auth"))
                .paths(PathSelectors.ant("/account"))
                .build()
                .apiInfo(getApiInfo());
    }

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("유저 정보 요청")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tcp.iamlazy.user"))
                .paths(PathSelectors.ant("/user"))
                .build()
                .apiInfo(getApiInfo());
    }

    @Bean
    public Docket reviewApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("회고 요청")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tcp.iamlazy.review"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    @Bean
    public Docket todoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("할일 요청")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tcp.iamlazy.todo"))
                .paths(PathSelectors.ant("/todo/*"))
                .build()
                .apiInfo(getApiInfo());
    }

    @Bean
    public Docket statisticApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("통계 요청")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tcp.iamlazy.stats"))
                .paths(PathSelectors.ant("/statistics/*"))
                .build()
                .apiInfo(getApiInfo());
    }

    @Bean
    public Docket settingApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("설정 요청")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tcp.iamlazy.setting"))
                .paths(PathSelectors.ant("/setting/*"))
                .build()
                .apiInfo(getApiInfo());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("A. 전체")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("나는 게으르다 API 명세")
                .description("I am Lazy 프로젝트에서 사용될 api 명세를 약술합니다.")
                .license("all rights reserved @joi")
                .version("latest")
                .build();
    }
}

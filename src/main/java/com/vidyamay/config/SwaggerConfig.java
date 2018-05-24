package com.vidyamay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Vidyamay Education Pvt Ltd.
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.vidyamay.controller"))
	      .paths(PathSelectors.ant("/vidyamay/v1/users/**"))
	      .build()
	      .apiInfo(apiInfo());
	}
	 
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
                .title("Vidyamay REST API POC")
                .description("Spring Boot + Spring REST + Spring Security + Hibernate + JPA + Swagger + MySql")
                .termsOfServiceUrl("http://www.vidyamay.com")
                .contact("Vidyamay Education Pvt Ltd")
                .license("Apache License Version 2.0")
                .version("0.0.1")
                .build();
    }
}
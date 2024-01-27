package com.demo.LibraryManagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {
	
	
	//Swagger-ui --> http://localhost:8081/swagger-ui/
	
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
					.groupName("public-apis")
					.apiInfo(getApiInfo())
					.select()
					.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
					.build();
		
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Library Management System")
				.description("API's created for Library Management System")
				.version("1")
				.build();
	}

} 

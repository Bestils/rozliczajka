package com.sticngo.rozliczajka.infrastructures.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;


@Configuration
@EnableSwagger2
class SwaggerConfig {

  @Bean
  public Docket restApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("REST API")
        .select()
        .apis(basePackage("com.sticngo.rozliczajka.infrastructures.rest"))
        .build();
  }

  @Bean
  public Docket webApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("WEB API")
        .select()
        .apis(basePackage("com.sticngo.rozliczajka.infrastructures.web"))
        .build();
  }

}

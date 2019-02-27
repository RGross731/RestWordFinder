package org.gross.ryan.restwordfinder.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("org.gross.ryan.restwordfinder.controller"))
            .paths(regex("/wordfinder")).build().groupName("Rest Word Finder")
            .tags(new Tag("rest-word-finder", "Rest Word Finder")).useDefaultResponseMessages(false)
            .protocols(new HashSet<String>(Arrays.asList("https")))
            .securitySchemes(new ArrayList<BasicAuth>(Arrays.asList(new BasicAuth("basicAuth"))))
            .apiInfo(new ApiInfoBuilder().title("Rest Word Finder").description(
                    "Rest API for finding instances of words from a provided dictionary in a provided grid of NxN characters")
                    .version("0.0.1-SNAPSHOT").build());
  }

  @Bean
  public UiConfiguration uiConfig() {
    return UiConfigurationBuilder.builder().validatorUrl("").build();
  }

}

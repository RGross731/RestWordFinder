package org.gross.ryan.restwordfinder.config;

import org.gross.ryan.restwordfinder.security.RequestQueryParamValidatorHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(this.requestQueryParamValidatorHandlerInterceptor())
            .addPathPatterns("/wordfinder");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Bean
  public RequestQueryParamValidatorHandlerInterceptor requestQueryParamValidatorHandlerInterceptor() {
    return new RequestQueryParamValidatorHandlerInterceptor();
  }

}

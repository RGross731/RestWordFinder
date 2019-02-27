package org.gross.ryan.restwordfinder.config;

import java.util.Arrays;

import org.gross.ryan.restwordfinder.security.ExceptionForwardingAccessDeniedHandler;
import org.gross.ryan.restwordfinder.security.ExceptionForwardingAuthenticationEntryPoint;
import org.gross.ryan.restwordfinder.security.InMemoryUserDetailsService;
import org.gross.ryan.restwordfinder.security.TemporaryRedirectStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.channel.RetryWithHttpsEntryPoint;
import org.springframework.security.web.access.channel.SecureChannelProcessor;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.requestMatchers().antMatchers("/wordfinder");

    http.csrf().disable();

    http.requiresChannel().anyRequest().requiresSecure()
            .channelProcessors(Arrays.asList(this.secureChannelProcessor()));

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeRequests().antMatchers(HttpMethod.POST).hasRole("USER");

    http.authorizeRequests().anyRequest().denyAll();

    http.httpBasic().authenticationEntryPoint(this.authenticationEntryPoint());

    http.exceptionHandling().accessDeniedHandler(this.accessDeniedHandler());
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.userDetailsService()).passwordEncoder(this.passwordEncoder());
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    return new InMemoryUserDetailsService();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new ExceptionForwardingAuthenticationEntryPoint();
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new ExceptionForwardingAccessDeniedHandler();
  }

  @Bean
  public SecureChannelProcessor secureChannelProcessor() {
    TemporaryRedirectStrategy temporaryRedirectStrategy = new TemporaryRedirectStrategy();

    RetryWithHttpsEntryPoint retryWithHttpsEntryPoint = new RetryWithHttpsEntryPoint();
    retryWithHttpsEntryPoint.setRedirectStrategy(temporaryRedirectStrategy);

    SecureChannelProcessor secureChannelProcessor = new SecureChannelProcessor();
    secureChannelProcessor.setEntryPoint(retryWithHttpsEntryPoint);

    return secureChannelProcessor;
  }

}

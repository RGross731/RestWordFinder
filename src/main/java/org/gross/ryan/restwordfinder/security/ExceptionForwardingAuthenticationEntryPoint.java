package org.gross.ryan.restwordfinder.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

public class ExceptionForwardingAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
          AuthenticationException e) throws IOException {

    request.setAttribute("javax.servlet.error.exception", e);
    response.addHeader("WWW-Authenticate", "Basic Realm=\"" + this.getRealmName() + "\"");
    response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    this.setRealmName("Rest Word Finder");
    super.afterPropertiesSet();
  }
}

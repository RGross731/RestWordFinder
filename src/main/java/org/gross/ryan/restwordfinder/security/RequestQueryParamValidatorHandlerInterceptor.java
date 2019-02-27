package org.gross.ryan.restwordfinder.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestQueryParamValidatorHandlerInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
          Object handler) {

    if (request.getParameterMap().entrySet().size() > 0) {
      throw new IllegalArgumentException("Query paramters not supported");
    }

    return true;
  }
}

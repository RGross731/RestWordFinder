package org.gross.ryan.restwordfinder.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class ExceptionForwardingAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
          AccessDeniedException e) throws IOException {

    request.setAttribute("javax.servlet.error.exception", e);
    response.sendError(HttpStatus.FORBIDDEN.value(), e.getMessage());
  }
}

package org.gross.ryan.restwordfinder.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;

public class TemporaryRedirectStrategy extends DefaultRedirectStrategy {

  @Override
  public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url)
          throws IOException {

    String redirectUrl = calculateRedirectUrl(request.getContextPath(), url);
    redirectUrl = response.encodeRedirectURL(redirectUrl);
    response.addHeader("Location", redirectUrl);
    response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
    response.flushBuffer();
  }
}

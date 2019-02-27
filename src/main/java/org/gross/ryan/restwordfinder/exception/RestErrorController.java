package org.gross.ryan.restwordfinder.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestErrorController implements ErrorController {

  @RequestMapping(value = "/error", produces = "application/json")
  public void handleError(HttpServletRequest request) throws Exception {
    Exception e = (Exception) request
            .getAttribute(DefaultErrorAttributes.class.getName() + ".ERROR");
    if (e == null) {
      e = (Exception) request.getAttribute("javax.servlet.error.exception");
    }

    throw e;
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }

}

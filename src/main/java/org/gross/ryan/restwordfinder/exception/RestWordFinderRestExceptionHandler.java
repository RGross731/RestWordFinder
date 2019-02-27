package org.gross.ryan.restwordfinder.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestWordFinderRestExceptionHandler {
  private static final Logger log = LoggerFactory
          .getLogger(RestWordFinderRestExceptionHandler.class);

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public String handleException(Exception e) {
    log.error(e.getMessage(), e);
    return "{\"error\":{\"status\":" + HttpStatus.INTERNAL_SERVER_ERROR.value() + ",\"type\":\""
            + e.getClass().getSimpleName() + "\",\"message\":\"" + e.getMessage() + "\"}}";
  }
}

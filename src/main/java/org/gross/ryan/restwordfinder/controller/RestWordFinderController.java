package org.gross.ryan.restwordfinder.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Api(tags = "rest-word-finder")
@RestController
@RequestMapping("/wordfinder")
public class RestWordFinderController {

  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 400, message = "Bad Request"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 500, message = "Internal Server Error") })
  @ApiOperation(httpMethod = "POST", consumes = "application/json", produces = "application/json", value = "Finds instances of words in a grid of NxN characters", authorizations = {
          @Authorization(value = "basicAuth") }, response = String.class)
  @PostMapping(consumes = "application/json", produces = "application/json")
  public String findWords(
          @ApiParam(value = "Word Finder request", required = true) @RequestBody String wordFinderRequest) {

    return "test";
  }

}

package org.gross.ryan.restwordfinder.controller;

import org.gross.ryan.restwordfinder.domain.WordFinderRequest;
import org.gross.ryan.restwordfinder.domain.WordFinderResponse;
import org.gross.ryan.restwordfinder.service.WordFinderService;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

  private static final Logger log = LoggerFactory.getLogger(RestWordFinderController.class);

  @Autowired
  private WordFinderService wordFinderService;

  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 400, message = "Bad Request"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 500, message = "Internal Server Error") })
  @ApiOperation(httpMethod = "POST", consumes = "application/json", produces = "application/json", value = "Finds instances of words in a grid of NxN characters", authorizations = {
          @Authorization(value = "basicAuth") }, response = WordFinderResponse.class)
  @PostMapping(consumes = "application/json", produces = "application/json")
  public WordFinderResponse findWords(
          @ApiParam(value = "Word finder request", required = true) @RequestBody WordFinderRequest wordFinderRequest)
          throws ValidationException {

    int gridSize = wordFinderRequest.getGridSize();
    if (gridSize <= 0) {
      throw new ValidationException("The value of gridSize must be a positive non-zero integer",
              "Invalid gridSize value of " + gridSize);
    }

    log.info("Request: " + wordFinderRequest);

    String gridString = wordFinderRequest.getGridString();
    gridString = ESAPI.validator().getValidInput("Grid String", gridString, "AlphaSpace",
            Integer.MAX_VALUE, false);
    gridString = gridString.toUpperCase();

    String[] dictionary = wordFinderRequest.getDictionary();
    for (String word : dictionary) {
      word = ESAPI.validator().getValidInput("Word", word, "Alpha", Integer.MAX_VALUE, false);
      word = word.toUpperCase();
    }

    return this.wordFinderService.findWords(gridSize, gridString, dictionary);
  }

}

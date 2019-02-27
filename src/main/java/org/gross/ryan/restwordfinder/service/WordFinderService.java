package org.gross.ryan.restwordfinder.service;

import org.gross.ryan.restwordfinder.domain.WordFinderResponse;
import org.springframework.stereotype.Service;

@Service
public class WordFinderService {

  public WordFinderResponse findWords(int gridSize, String gridString, String[] dictionary) {
    WordFinderResponse wfr = new WordFinderResponse();
    return wfr;
  }

}

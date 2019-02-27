package org.gross.ryan.restwordfinder.service;

import java.util.List;

import org.gross.ryan.restwordfinder.domain.WordFinderResponse;
import org.springframework.stereotype.Service;

@Service
public class WordFinderService {

  public WordFinderResponse findWords(int gridSize, String gridString, List<String> dictionary) {

    if (gridString.length() < gridSize * gridSize) {
      gridString = String.format("%-" + gridSize * gridSize + "." + gridSize * gridSize + "s",
              gridString);
    }

    System.out.println(gridString + "*");

    WordFinderResponse wfr = new WordFinderResponse();

    for (String word : dictionary) {
      if (word.length() > gridSize) {
        dictionary.remove(word);
        wfr.addMissingWord(word);
      }
    }

    return wfr;
  }

}

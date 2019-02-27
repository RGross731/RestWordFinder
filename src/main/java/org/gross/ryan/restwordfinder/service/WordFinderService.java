package org.gross.ryan.restwordfinder.service;

import java.util.List;

import org.gross.ryan.restwordfinder.domain.WordFinderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WordFinderService {

  private static final Logger log = LoggerFactory.getLogger(WordFinderService.class);

  public WordFinderResponse findWords(int gridSize, String gridString, List<String> dictionary) {

    int gridSizeSq = gridSize * gridSize;

    if (gridString.length() < gridSize * gridSize) {
      gridString = String.format("%-" + gridSizeSq + "." + gridSizeSq + "s", gridString);
    }

    log.info("Derived gridString: " + gridString);

    WordFinderResponse wfr = new WordFinderResponse();

    for (String word : dictionary) {
      if (word.length() > gridSize && !wfr.getMissingWords().contains(word)) {
        wfr.addMissingWord(word);
      }
    }
    dictionary.removeAll(wfr.getMissingWords());

    for (String word : dictionary) {
      if (wfr.getFoundWords().contains(word) || wfr.getMissingWords().contains(word)) {
        continue;
      }

      if (this.wordSearch(word, gridString)) {
        wfr.addFoundWord(word);
      } else {
        wfr.addMissingWord(word);
      }

    }

    return wfr;
  }

  private boolean wordSearch(String word, String gridString) {
    return false;
  }

}

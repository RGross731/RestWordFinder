package org.gross.ryan.restwordfinder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.gross.ryan.restwordfinder.domain.WordFinderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WordFinderService {

  public static final Logger log = LoggerFactory.getLogger(WordFinderService.class);

  public WordFinderResponse findWords(int gridSize, String gridString, List<String> dictionary) {

    gridString = this.padGridString(gridSize, gridString);
    log.info("Derived gridString: " + gridString);

    WordFinderResponse wfr = new WordFinderResponse();

    for (String word : dictionary) {
      if (word.length() > gridSize
              && !wfr.getMissingWords().stream().anyMatch(word::equalsIgnoreCase)) {
        wfr.addMissingWord(word);
      }
    }
    dictionary.removeAll(wfr.getMissingWords());

    this.wordSearch(dictionary, gridString, gridSize, wfr);

    return wfr;
  }

  public String padGridString(int gridSize, String gridString) {
    if (gridString.length() < gridSize * gridSize) {
      return String.format("%-" + gridSize * gridSize + "." + gridSize * gridSize + "s",
              gridString);
    } else {
      return gridString;
    }
  }

  public void wordSearch(List<String> dictionary, String gridString, int gridSize,
          WordFinderResponse wfr) {

    List<String> horizontalRows = this.constructHorizontalRows(gridString, gridSize);

    List<String> verticalRows = this.constructVerticalRows(gridString, gridSize);

    List<String> forwardDiagonalRows = this.constructDiagonalRows(gridString, gridSize);

    String reversedGridString = this.reverseGridString(horizontalRows);
    List<String> backwardDiagonalRows = this.constructDiagonalRows(reversedGridString, gridSize);

    // Check each row for a string match with short circuit eval when found
    for (String word : dictionary) {
      if (wfr.getFoundWords().stream().anyMatch(word::equalsIgnoreCase)
              || wfr.getMissingWords().stream().anyMatch(word::equalsIgnoreCase)) {
        continue;
      }

      if (this.checkRows(horizontalRows, word) || this.checkRows(verticalRows, word)
              || this.checkRows(forwardDiagonalRows, word)
              || this.checkRows(backwardDiagonalRows, word)) {
        wfr.addFoundWord(word);
      } else {
        wfr.addMissingWord(word);
      }
    }
  }

  public String reverseGridString(List<String> horizontalRows) {
    return horizontalRows.stream().map(s -> {
      return new StringBuilder().append(s).reverse().toString();
    }).collect(Collectors.joining());
  }

  public List<String> constructHorizontalRows(String gridString, int gridSize) {
    // There are gridSize horizontal rows to search forwards and backwards
    List<String> rows = new ArrayList<String>();
    for (int i = 0; i < gridSize; i++) {
      String row = "";
      for (int j = 0; j < gridSize; j++) {
        row += String.valueOf(gridString.charAt(i * gridSize + j));
      }
      rows.add(row);
    }
    return rows;
  }

  public List<String> constructVerticalRows(String gridString, int gridSize) {
    // There are gridSize vertical rows to search forwards and backwards
    List<String> rows = new ArrayList<String>();
    for (int i = 0; i < gridSize; i++) {
      String row = "";
      for (int j = 0; j < gridSize; j++) {
        row += String.valueOf(gridString.charAt(i + j * gridSize));
      }
      rows.add(row);
    }
    return rows;
  }

  public List<String> constructDiagonalRows(String gridString, int gridSize) {
    // There are 2 * gridSize - 1 diagonal rows to search
    List<String> rows = new ArrayList<String>();
    for (int i = 0; i < gridSize * gridSize; i++) {
      String row = "";
      for (int j = 0; j < i + 1; j++) {
        // System.out.println("j = " + i);
        if (i + j * (gridSize - 1) < gridSize * gridSize) {
          row += String.valueOf(gridString.charAt(i + j * (gridSize - 1)));
        }
      }
      if (i + 1 >= gridSize) {
        i += (gridSize - 1);
      }
      rows.add(row);
    }
    return rows;
  }

  public boolean checkRows(List<String> rows, String word) {
    for (String row : rows) {
      if (row.contains(word.toUpperCase())) {
        return true;
      }
      String reversedRow = new StringBuilder().append(row).reverse().toString();
      if (reversedRow.contains(word.toUpperCase())) {
        return true;
      }
    }
    return false;
  }

}

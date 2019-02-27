package org.gross.ryan.restwordfinder.domain;

import java.util.Arrays;

public class WordFinderResponse {
  private String[] foundWords;
  private String[] missingWords;

  public String[] getFoundWords() {
    return this.foundWords;
  }

  public void setFoundWords(String[] foundWords) {
    this.foundWords = foundWords;
  }

  public String[] getMissingWords() {
    return this.missingWords;
  }

  public void setMissingWords(String[] missingWords) {
    this.missingWords = missingWords;
  }

  @Override
  public String toString() {
    return "WordFinderResponse [foundWords=" + Arrays.toString(foundWords) + ", missingWords ="
            + Arrays.toString(missingWords) + "]";
  }
}

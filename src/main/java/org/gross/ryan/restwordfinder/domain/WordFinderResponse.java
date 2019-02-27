package org.gross.ryan.restwordfinder.domain;

import java.util.ArrayList;
import java.util.List;

public class WordFinderResponse {
  private List<String> foundWords;
  private List<String> missingWords;

  public WordFinderResponse() {
    this.foundWords = new ArrayList<>();
    this.missingWords = new ArrayList<>();
  }

  public void addFoundWord(String word) {
    this.foundWords.add(word);
  }

  public List<String> getFoundWords() {
    return this.foundWords;
  }

  public void setFoundWords(List<String> foundWords) {
    this.foundWords = foundWords;
  }

  public void addMissingWord(String word) {
    this.missingWords.add(word);
  }

  public List<String> getMissingWords() {
    return this.missingWords;
  }

  public void setMissingWords(List<String> missingWords) {
    this.missingWords = missingWords;
  }

  @Override
  public String toString() {
    return "WordFinderResponse [foundWords=" + foundWords + ", missingWords=" + missingWords + "]";
  }
}

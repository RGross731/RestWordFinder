package org.gross.ryan.restwordfinder.domain;

import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel("Request")
public class WordFinderRequest {
  private int gridSize;
  private String gridString;
  private List<String> dictionary;

  public int getGridSize() {
    return this.gridSize;
  }

  public void setGridSize(int gridSize) {
    this.gridSize = gridSize;
  }

  public String getGridString() {
    return this.gridString;
  }

  public void setGridString(String gridString) {
    this.gridString = gridString;
  }

  public List<String> getDictionary() {
    return this.dictionary;
  }

  public void setDictionary(List<String> dictionary) {
    this.dictionary = dictionary;
  }

  @Override
  public String toString() {
    return "WordFinderRequest [gridSize=" + gridSize + ", gridString=" + gridString + "dictionary="
            + dictionary + "]";
  }

}

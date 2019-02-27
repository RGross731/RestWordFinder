package org.gross.ryan.restwordfinder.domain;

import java.util.Arrays;

import io.swagger.annotations.ApiModel;

@ApiModel("Request")
public class WordFinderRequest {
  private int gridSize;
  private String gridString;
  private String[] dictionary;

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

  public String[] getDictionary() {
    return this.dictionary;
  }

  public void setDictionary(String[] dictionary) {
    this.dictionary = dictionary;
  }

  @Override
  public String toString() {
    return "WordFinderRequest [gridSize=" + gridSize + ", gridString=" + gridString + "dictionary="
            + Arrays.toString(dictionary) + "]";
  }

}

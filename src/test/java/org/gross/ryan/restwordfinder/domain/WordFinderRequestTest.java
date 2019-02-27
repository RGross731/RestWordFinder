package org.gross.ryan.restwordfinder.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class WordFinderRequestTest {

  private WordFinderRequest wfr;

  @Before
  public void setUp() {
    this.wfr = new WordFinderRequest();
    this.wfr.setGridSize(0);
    this.wfr.setGridString("defaultGridString");
    List<String> dictionary = new ArrayList<>();
    dictionary.add("defaultWord");
    this.wfr.setDictionary(dictionary);
  }

  @Test
  public void testGridSize() {
    this.wfr.setGridSize(1);
    assertEquals(1, this.wfr.getGridSize());
  }

  @Test
  public void testGridString() {
    this.wfr.setGridString("newGridString");
    assertEquals("newGridString", this.wfr.getGridString());
  }

  @Test
  public void testDictionary() {
    List<String> dictionary = new ArrayList<>();
    dictionary.add("newWord");
    dictionary.add("anotherWord");
    this.wfr.setDictionary(dictionary);
    assertEquals(2, this.wfr.getDictionary().size());
    assertEquals("newWord", this.wfr.getDictionary().get(0));
    assertEquals("anotherWord", this.wfr.getDictionary().get(1));
  }

  @Test
  public void testToString() {
    assertEquals(
            "WordFinderRequest [gridSize=0, gridString=defaultGridString, dictionary=[defaultWord]]",
            this.wfr.toString());
  }

}

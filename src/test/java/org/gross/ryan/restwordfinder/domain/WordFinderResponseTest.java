package org.gross.ryan.restwordfinder.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class WordFinderResponseTest {

  private WordFinderResponse wfr;

  @Before
  public void setUp() {
    this.wfr = new WordFinderResponse();
    this.wfr.addFoundWord("defaultFoundWord");
    this.wfr.addMissingWord("defaultMissingWord");
  }

  @Test
  public void testFoundWords() {
    List<String> foundWords = new ArrayList<>();
    foundWords.add("newFoundWord");
    this.wfr.setFoundWords(foundWords);
    this.wfr.addFoundWord("anotherFoundWord");
    assertEquals(2, this.wfr.getFoundWords().size());
    assertEquals("newFoundWord", this.wfr.getFoundWords().get(0));
    assertEquals("anotherFoundWord", this.wfr.getFoundWords().get(1));
  }

  @Test
  public void testMissingWords() {
    List<String> missingWords = new ArrayList<>();
    missingWords.add("newMissingWord");
    this.wfr.setMissingWords(missingWords);
    this.wfr.addMissingWord("anotherMissingWord");
    assertEquals(2, this.wfr.getMissingWords().size());
    assertEquals("newMissingWord", this.wfr.getMissingWords().get(0));
    assertEquals("anotherMissingWord", this.wfr.getMissingWords().get(1));
  }

  @Test
  public void testToString() {
    assertEquals(
            "WordFinderResponse [foundWords=[defaultFoundWord], missingWords=[defaultMissingWord]]",
            this.wfr.toString());
  }

}

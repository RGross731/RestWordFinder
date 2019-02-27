package org.gross.ryan.restwordfinder.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.gross.ryan.restwordfinder.domain.WordFinderResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WordFinderServiceTest {

  private WordFinderService wfs;

  @Before
  public void setUp() {
    this.wfs = new WordFinderService();
  }

  @Test
  public void testReverseGridString() {
    List<String> horizontalRows;
    String reversedGridString;

    horizontalRows = new ArrayList<>();
    horizontalRows.add("A");
    reversedGridString = this.wfs.reverseGridString(horizontalRows);
    assertEquals("A", reversedGridString);

    horizontalRows = new ArrayList<>();
    horizontalRows.add("AB");
    horizontalRows.add("CD");
    reversedGridString = this.wfs.reverseGridString(horizontalRows);
    assertEquals("BADC", reversedGridString);

    horizontalRows = new ArrayList<>();
    horizontalRows.add("ABC");
    horizontalRows.add("DEF");
    horizontalRows.add("GHI");
    reversedGridString = this.wfs.reverseGridString(horizontalRows);
    assertEquals("CBAFEDIHG", reversedGridString);
  }

  @Test
  public void testConstructHorizontalRows() {
    List<String> rows;

    rows = this.wfs.constructHorizontalRows("A", 1);
    assertEquals(1, rows.size());
    assertEquals("A", rows.get(0));

    rows = this.wfs.constructHorizontalRows("ABCD", 2);
    assertEquals(2, rows.size());
    assertEquals("AB", rows.get(0));
    assertEquals("CD", rows.get(1));

    rows = this.wfs.constructHorizontalRows("ABCDEFGHI", 3);
    assertEquals(3, rows.size());
    assertEquals("ABC", rows.get(0));
    assertEquals("DEF", rows.get(1));
    assertEquals("GHI", rows.get(2));
  }

  @Test
  public void testConstructVerticalRows() {
    List<String> rows;

    rows = this.wfs.constructVerticalRows("A", 1);
    assertEquals(1, rows.size());
    assertEquals("A", rows.get(0));

    rows = this.wfs.constructVerticalRows("ABCD", 2);
    assertEquals(2, rows.size());
    assertEquals("AC", rows.get(0));
    assertEquals("BD", rows.get(1));

    rows = this.wfs.constructVerticalRows("ABCDEFGHI", 3);
    assertEquals(3, rows.size());
    assertEquals("ADG", rows.get(0));
    assertEquals("BEH", rows.get(1));
    assertEquals("CFI", rows.get(2));
  }

  @Test
  public void testConstructForwardDiagonalRows() {
    List<String> rows;

    rows = this.wfs.constructDiagonalRows("A", 1);
    assertEquals(1, rows.size());
    assertEquals("A", rows.get(0));

    rows = this.wfs.constructDiagonalRows("ABCD", 2);
    assertEquals(3, rows.size());
    assertEquals("A", rows.get(0));
    assertEquals("BC", rows.get(1));
    assertEquals("D", rows.get(2));

    rows = this.wfs.constructDiagonalRows("ABCDEFGHI", 3);
    assertEquals(5, rows.size());
    assertEquals("A", rows.get(0));
    assertEquals("BD", rows.get(1));
    assertEquals("CEG", rows.get(2));
    assertEquals("FH", rows.get(3));
    assertEquals("I", rows.get(4));
  }

  @Test
  public void testConstructBackwardDiagonalRows() {
    List<String> rows;

    rows = this.wfs.constructDiagonalRows(
            this.wfs.reverseGridString(this.wfs.constructHorizontalRows("A", 1)), 1);
    assertEquals(1, rows.size());
    assertEquals("A", rows.get(0));

    rows = this.wfs.constructDiagonalRows(
            this.wfs.reverseGridString(this.wfs.constructHorizontalRows("ABCD", 2)), 2);
    assertEquals(3, rows.size());
    assertEquals("B", rows.get(0));
    assertEquals("AD", rows.get(1));
    assertEquals("C", rows.get(2));

    rows = this.wfs.constructDiagonalRows(
            this.wfs.reverseGridString(this.wfs.constructHorizontalRows("ABCDEFGHI", 3)), 3);
    assertEquals(5, rows.size());
    assertEquals("C", rows.get(0));
    assertEquals("BF", rows.get(1));
    assertEquals("AEI", rows.get(2));
    assertEquals("DH", rows.get(3));
    assertEquals("G", rows.get(4));
  }

  @Test
  public void testCheckRowsFoundForward() {
    List<String> rows;

    rows = new ArrayList<>();
    rows.add("A");
    assertTrue(this.wfs.checkRows(rows, "a"));

    rows = new ArrayList<>();
    rows.add("AB");
    rows.add("CD");
    assertTrue(this.wfs.checkRows(rows, "A"));
    assertTrue(this.wfs.checkRows(rows, "cD"));
  }

  @Test
  public void testCheckRowsFoundReverse() {
    List<String> rows;

    rows = new ArrayList<>();
    rows.add("A");
    assertTrue(this.wfs.checkRows(rows, "a"));

    rows = new ArrayList<>();
    rows.add("AB");
    rows.add("CD");
    assertTrue(this.wfs.checkRows(rows, "BA"));
    assertTrue(this.wfs.checkRows(rows, "Dc"));
  }

  @Test
  public void testCheckRowsNotFound() {
    List<String> rows;

    rows = new ArrayList<>();
    rows.add("A");
    assertFalse(this.wfs.checkRows(rows, "b"));

    rows = new ArrayList<>();
    rows.add("AB");
    rows.add("CD");
    assertFalse(this.wfs.checkRows(rows, "BB"));
    assertFalse(this.wfs.checkRows(rows, "dD"));
  }

  @Test
  public void testWordSearchDuplicateFoundWord() {
    List<String> dictionary;
    WordFinderResponse wfr;

    dictionary = new ArrayList<>();
    dictionary.add("a");
    dictionary.add("A");
    wfr = new WordFinderResponse();
    this.wfs.wordSearch(dictionary, "A", 1, wfr);
    assertEquals(0, wfr.getMissingWords().size());
    assertEquals(1, wfr.getFoundWords().size());
    assertEquals("a", wfr.getFoundWords().get(0));
  }

  @Test
  public void testWordSearchDuplicateMissingWord() {
    List<String> dictionary;
    WordFinderResponse wfr;

    dictionary = new ArrayList<>();
    dictionary.add("b");
    dictionary.add("B");
    wfr = new WordFinderResponse();
    this.wfs.wordSearch(dictionary, "A", 1, wfr);
    assertEquals(0, wfr.getFoundWords().size());
    assertEquals(1, wfr.getMissingWords().size());
    assertEquals("b", wfr.getMissingWords().get(0));
  }

  @Test
  public void testWordSearchFoundHorizontal() {
    List<String> dictionary;
    WordFinderResponse wfr;

    dictionary = new ArrayList<>();
    dictionary.add("ab");
    dictionary.add("Dc");
    wfr = new WordFinderResponse();
    this.wfs.wordSearch(dictionary, "ABCD", 2, wfr);
    assertEquals(0, wfr.getMissingWords().size());
    assertEquals(2, wfr.getFoundWords().size());
    assertEquals("ab", wfr.getFoundWords().get(0));
    assertEquals("Dc", wfr.getFoundWords().get(1));
  }

  @Test
  public void testWordSearchFoundVertical() {
    List<String> dictionary;
    WordFinderResponse wfr;

    dictionary = new ArrayList<>();
    dictionary.add("Ac");
    dictionary.add("DB");
    wfr = new WordFinderResponse();
    this.wfs.wordSearch(dictionary, "ABCD", 2, wfr);
    assertEquals(0, wfr.getMissingWords().size());
    assertEquals(2, wfr.getFoundWords().size());
    assertEquals("Ac", wfr.getFoundWords().get(0));
    assertEquals("DB", wfr.getFoundWords().get(1));
  }

  @Test
  public void testWordSearchFoundForwardDiagonal() {
    List<String> dictionary;
    WordFinderResponse wfr;

    dictionary = new ArrayList<>();
    dictionary.add("cB");
    wfr = new WordFinderResponse();
    this.wfs.wordSearch(dictionary, "ABCD", 2, wfr);
    assertEquals(0, wfr.getMissingWords().size());
    assertEquals(1, wfr.getFoundWords().size());
    assertEquals("cB", wfr.getFoundWords().get(0));
  }

  @Test
  public void testWordSearchFoundBackwardDiagonal() {
    List<String> dictionary;
    WordFinderResponse wfr;

    dictionary = new ArrayList<>();
    dictionary.add("Ad");
    wfr = new WordFinderResponse();
    this.wfs.wordSearch(dictionary, "ABCD", 2, wfr);
    assertEquals(0, wfr.getMissingWords().size());
    assertEquals(1, wfr.getFoundWords().size());
    assertEquals("Ad", wfr.getFoundWords().get(0));
  }

  @Test
  public void testWordSearchNotFound() {
    List<String> dictionary;
    WordFinderResponse wfr;

    dictionary = new ArrayList<>();
    dictionary.add("e");
    wfr = new WordFinderResponse();
    this.wfs.wordSearch(dictionary, "ABCD", 2, wfr);
    assertEquals(0, wfr.getFoundWords().size());
    assertEquals(1, wfr.getMissingWords().size());
    assertEquals("e", wfr.getMissingWords().get(0));
  }

  @Test
  public void testPadGridStringPaddingNeeded() {
    assertEquals("ABC ", this.wfs.padGridString(2, "ABC"));
  }

  @Test
  public void testPadGridStringPaddingNotNeeded() {
    assertEquals("ABCD", this.wfs.padGridString(2, "ABCD"));
  }

  @Test
  public void testFindWordsLongWord() {
    List<String> dictionary;
    WordFinderResponse wfr;

    dictionary = new ArrayList<>();
    dictionary.add("ab");
    wfr = this.wfs.findWords(1, "A", dictionary);
    assertEquals(0, wfr.getFoundWords().size());
    assertEquals(1, wfr.getMissingWords().size());
    assertEquals("ab", wfr.getMissingWords().get(0));
  }

  @Test
  public void testFindWordsDuplicateLongWord() {
    List<String> dictionary;
    WordFinderResponse wfr;

    dictionary = new ArrayList<>();
    dictionary.add("ab");
    dictionary.add("Ab");
    wfr = this.wfs.findWords(1, "A", dictionary);
    assertEquals(0, wfr.getFoundWords().size());
    assertEquals(1, wfr.getMissingWords().size());
    assertEquals("ab", wfr.getMissingWords().get(0));
  }

  @Test
  public void testFindWords() {
    List<String> dictionary;
    WordFinderResponse wfr;

    dictionary = new ArrayList<>();
    dictionary.add("ab");
    dictionary.add("AB");
    dictionary.add("cd");
    dictionary.add("Ac");
    dictionary.add("bD");
    dictionary.add("AD");
    dictionary.add("cB");
    dictionary.add("e");
    dictionary.add("e");
    dictionary.add("aBc");
    wfr = this.wfs.findWords(2, "ABCD", dictionary);
    assertEquals(2, wfr.getMissingWords().size());
    assertEquals("aBc", wfr.getMissingWords().get(0));
    assertEquals("e", wfr.getMissingWords().get(1));
    assertEquals(6, wfr.getFoundWords().size());
    assertEquals("ab", wfr.getFoundWords().get(0));
    assertEquals("cd", wfr.getFoundWords().get(1));
    assertEquals("Ac", wfr.getFoundWords().get(2));
    assertEquals("bD", wfr.getFoundWords().get(3));
    assertEquals("AD", wfr.getFoundWords().get(4));
    assertEquals("cB", wfr.getFoundWords().get(5));
  }

}

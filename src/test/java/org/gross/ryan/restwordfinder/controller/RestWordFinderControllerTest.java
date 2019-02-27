package org.gross.ryan.restwordfinder.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.gross.ryan.restwordfinder.domain.WordFinderRequest;
import org.gross.ryan.restwordfinder.domain.WordFinderResponse;
import org.gross.ryan.restwordfinder.service.WordFinderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.owasp.esapi.errors.ValidationException;

@RunWith(MockitoJUnitRunner.class)
public class RestWordFinderControllerTest {

  @InjectMocks
  private RestWordFinderController restWordFinderController = new RestWordFinderController();

  @Mock
  private WordFinderService wordFinderService;

  @Test
  public void testFindWordsInvalidGridSize() {
    WordFinderRequest wfr = new WordFinderRequest();
    wfr.setGridSize(0);
    try {
      this.restWordFinderController.findWords(wfr);
      fail();
    } catch (ValidationException e) {
      // Expected behavior
    }
  }

  @Test
  public void testFindWordsInvalidGridString() {
    WordFinderRequest wfr = new WordFinderRequest();
    wfr.setGridSize(1);
    wfr.setGridString("ab");
    try {
      this.restWordFinderController.findWords(wfr);
      fail();
    } catch (ValidationException e) {
      // Expected behavior
    }
  }

  @Test
  public void testFindWords() throws ValidationException {
    List<String> dictionary = new ArrayList<String>();
    dictionary.add("ab");
    WordFinderRequest wfr = new WordFinderRequest();
    wfr.setGridSize(2);
    wfr.setGridString("abcd");
    wfr.setDictionary(dictionary);
    WordFinderResponse response = new WordFinderResponse();
    response.addFoundWord("ab");
    when(this.wordFinderService.findWords(2, "ABCD", dictionary)).thenReturn(response);
    WordFinderResponse res = this.restWordFinderController.findWords(wfr);
    assertEquals(0, res.getMissingWords().size());
    assertEquals(1, res.getFoundWords().size());
    assertEquals("ab", res.getFoundWords().get(0));
  }

}

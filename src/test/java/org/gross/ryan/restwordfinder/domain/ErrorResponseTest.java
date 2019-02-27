package org.gross.ryan.restwordfinder.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ErrorResponseTest {

  private ErrorResponse errorResponse;

  @Before
  public void setUp() {
    this.errorResponse = new ErrorResponse(0, "defaultType", "defaultMessage");
  }

  @Test
  public void testStatus() {
    this.errorResponse.setStatus(1);
    assertEquals(1, this.errorResponse.getStatus());
  }

  @Test
  public void testType() {
    this.errorResponse.setType("newType");
    assertEquals("newType", this.errorResponse.getType());
  }

  @Test
  public void testMessage() {
    this.errorResponse.setMessage("newMessage");
    assertEquals("newMessage", this.errorResponse.getMessage());
  }

  @Test
  public void testToString() {
    assertEquals("ErrorResponse [status=0, type=defaultType, message=defaultMessage]",
            this.errorResponse.toString());
  }

}

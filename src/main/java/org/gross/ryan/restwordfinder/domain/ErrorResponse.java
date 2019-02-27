package org.gross.ryan.restwordfinder.domain;

public class ErrorResponse {
  private int status;
  private String type;
  private String message;

  public ErrorResponse(int status, String type, String message) {
    this.setStatus(status);
    this.setType(type);
    this.setMessage(message);
  }

  public int getStatus() {
    return this.status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "ErrorResponse [status=" + status + ", type=" + type + ", message=" + message + "]";
  }
}

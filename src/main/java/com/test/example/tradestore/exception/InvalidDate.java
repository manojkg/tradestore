package com.test.example.tradestore.exception;

public class InvalidDate extends  RuntimeException {

  private static final long serialVersionUID = -2408430251513359267L;

  public InvalidDate(String message) {
    super(message);
  }
}

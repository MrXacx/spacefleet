package com.mrxacx.spacefleet.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnexpectedHttpResponseException extends RuntimeException {
  public UnexpectedHttpResponseException(String message) {
    super(message);
  }
}

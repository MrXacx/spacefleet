package com.mrxacx.spacefleet.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFoundItemHttpException extends RuntimeException {
  public NotFoundItemHttpException(String message) {
    super(message);
  }
}

package com.mrxacx.spacefleet.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ManyFoundItemsHttpException extends RuntimeException{
  public ManyFoundItemsHttpException(String message) {
    super(message);
  }
}

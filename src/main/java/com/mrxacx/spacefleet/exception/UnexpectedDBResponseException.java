package com.mrxacx.spacefleet.exception;

public class UnexpectedDBResponseException extends RuntimeException{
  public UnexpectedDBResponseException(String message){
    super(message);
  }
}

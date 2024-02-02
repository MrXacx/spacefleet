package com.mrxacx.spacefleet.controller.dto;

public abstract class ParserDTO {
  protected Long parseStringAttributeToLong(String string) {
    return Long.parseLong(string.replace(",", ""));
  }
  
  protected Double parseStringAttributeToDouble(String string) {
    return Double.parseDouble(string.replace(",", ""));
  }
}

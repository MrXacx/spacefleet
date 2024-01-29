package com.mrxacx.spacefleet.service.impl;

import com.mrxacx.spacefleet.model.Spaceship;
import com.mrxacx.spacefleet.service.IWebClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SWAPIClient extends IWebClientService {
  public SWAPIClient(){
    super(new RestTemplate(), "");
  }
  @Override
  public ResponseEntity<> get() {
    return  api.getForEntity(uri+path, Spaceship.class).getBody();
  }
}

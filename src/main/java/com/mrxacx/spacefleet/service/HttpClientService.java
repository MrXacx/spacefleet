package com.mrxacx.spacefleet.service;

import com.mrxacx.spacefleet.exception.UnexpectedHttpResponseException;
import com.mrxacx.spacefleet.model.IModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author ariel
 * @since 1.0
 */
@RequiredArgsConstructor
@Service
public abstract class HttpClientService {
  final protected RestTemplate api;
  final protected String uri;
  
  protected abstract IModel getModel(String path, Class<? extends IModel> modelClass);
  
  protected abstract List<? extends IModel> getModelList(String path, Class<? extends IModel> modelClass);
  
  protected void catchUnsucessfulRequest(ResponseEntity<?> response) {
    if (!response.getStatusCode().is2xxSuccessful()) { // If the HTTP status code be different of 2XX
      throw new UnexpectedHttpResponseException("Falha na pesquisa: " + response.toString());
    }
  }
}
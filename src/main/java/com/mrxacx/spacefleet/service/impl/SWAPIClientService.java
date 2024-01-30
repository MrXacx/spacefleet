package com.mrxacx.spacefleet.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrxacx.spacefleet.exception.ManyFoundItemsHttpException;
import com.mrxacx.spacefleet.exception.NotFoundItemHttpException;
import com.mrxacx.spacefleet.exception.UnexpectedHttpResponseException;
import com.mrxacx.spacefleet.model.IModel;
import com.mrxacx.spacefleet.model.Spaceship;
import com.mrxacx.spacefleet.service.HttpClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ariel
 * @since 1.0
 */
public class SWAPIClientService extends HttpClientService {
  public SWAPIClientService() {
    super(new RestTemplate(), "https://swapi.dev/api");
  }
  
  public Spaceship fetchSpaceship(String model) {
    final List<? extends IModel> result = getModelList("/starship/?search=" + model, Spaceship.class); // Fetch Spaceship list for model
    
    if (result.isEmpty()) {
      throw new NotFoundItemHttpException("Modelo de espaçonave não encontrado.");
    } else if (result.size() > 1) {
      throw new ManyFoundItemsHttpException("Mais de um modelo foi encontrado. Por favor, seja mais específico!");
    }
    
    final IModel spaceship = result.getFirst();
    
    if (!spaceship.getClass().equals(Spaceship.class)) {
      throw new UnexpectedHttpResponseException("Item encontrado não condiz com o modelo esperado");
    }
    
    return (Spaceship) spaceship;
  }
  
  @Override
  protected IModel getModel(String path, Class<? extends IModel> modelClass) {
    final ResponseEntity<? extends IModel> response = api.getForEntity(uri + path, modelClass); // Request
    catchUnsucessfulRequest(response); // possible throw
    return response.getBody();
  }
  
  @Override
  protected List<? extends IModel> getModelList(String path, Class<? extends IModel> modelClass) {
    final ResponseEntity<String> response = api.getForEntity(uri + path, String.class); // Request
    catchUnsucessfulRequest(response); // possible throw
    
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      final JsonNode jsonTree = objectMapper.readTree(response.getBody()).get("results"); // Get the attribute "results" from json
      
      final ArrayList<IModel> models = new ArrayList<>();
      for (JsonNode node : jsonTree) {
        models.add(
            objectMapper.treeToValue(node, modelClass)
        );
      }
      
      return models;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

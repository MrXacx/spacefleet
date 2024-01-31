package com.mrxacx.spacefleet.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrxacx.spacefleet.controller.dto.IModelDTO;
import com.mrxacx.spacefleet.controller.dto.impl.SpaceshipDTO;
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
    super(new RestTemplate(), "https://swapi.dev/api/");
  }
  
  public Spaceship fetchSpaceship(String model) {
    final List<? extends IModelDTO> result = getModelList("/starships/?search=" + model, SpaceshipDTO.class); // Fetch Spaceship list for model
    
    if (result.isEmpty()) {
      throw new NotFoundItemHttpException("Modelo de espaçonave não encontrado.");
    } else if (result.size() > 1) {
      throw new ManyFoundItemsHttpException("Mais de um modelo foi encontrado. Por favor, seja mais específico!");
    }
    
    IModelDTO modelDTO = result.getFirst();
    
    if (!modelDTO.getClass().equals(SpaceshipDTO.class)) {
      throw new UnexpectedHttpResponseException("Item encontrado não condiz com o modelo esperado");
    }
    
    SpaceshipDTO spaceshipDTO = (SpaceshipDTO) modelDTO;
    return Spaceship
        .builder()
        .name(spaceshipDTO.getName())
        .model(spaceshipDTO.getModel())
        .length(spaceshipDTO.getLength())
        .crew(spaceshipDTO.getCrew())
        .passengers((spaceshipDTO.getPassengers()))
        .cargoCapacity(spaceshipDTO.getCargo_capacity())
        .cost(spaceshipDTO.getCost_in_credits())
        .manufacturer(spaceshipDTO.getManufacturer())
        .hyperdriveRating(spaceshipDTO.getHyperdrive_rating())
        .build();
  }
  
  @Override
  protected IModel getModel(String path, Class<? extends IModel> modelClass) {
    final ResponseEntity<? extends IModel> response = api.getForEntity(uri + path, modelClass); // Request
    catchUnsucessfulRequest(response); // possible throw
    return response.getBody();
  }
  
  @Override
  protected List<? extends IModelDTO> getModelList(String path, Class<? extends IModelDTO> modelDTOClass) {
    final ResponseEntity<String> response = api.getForEntity(uri + path, String.class); // Request
    catchUnsucessfulRequest(response); // possible throw
    
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      final JsonNode jsonTree = objectMapper.readTree(response.getBody()).get("results"); // Get the attribute "results" from json
      
      final ArrayList<IModelDTO> models = new ArrayList<>();
      for (JsonNode node : jsonTree) {
        models.add(
            objectMapper.treeToValue(node, modelDTOClass)
        );
      }
      
      return models;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

package com.sistema_de_alertas.Sistema_de_Alertas.app.service;

import com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.request.PlantRequest;
import com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.response.ListPlantResponse;
import com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.response.PlantResponse;

import java.util.List;

public interface PlantService {

    PlantResponse findById(Long id);

    List<ListPlantResponse> findAllPlant();

    PlantResponse findByNamePlant(String namePlant);

    PlantResponse savePlant(Long id, PlantRequest plantRequest);

    PlantResponse updatePlant(Long id, PlantRequest plantRequest);

}

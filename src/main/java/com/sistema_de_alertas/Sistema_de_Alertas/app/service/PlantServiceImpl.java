package com.sistema_de_alertas.Sistema_de_Alertas.app.service;

import com.sistema_de_alertas.Sistema_de_Alertas.app.mappers.PlantMapper;
import com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.request.PlantRequest;
import com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.response.ListPlantResponse;
import com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.response.PlantResponse;
import com.sistema_de_alertas.Sistema_de_Alertas.app.model.entity.Plant;
import com.sistema_de_alertas.Sistema_de_Alertas.app.repository.PlantRepository;

import com.sistema_de_alertas.Sistema_de_Alertas.auth.entity.UserEntity;
import com.sistema_de_alertas.Sistema_de_Alertas.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;


@Service
public class PlantServiceImpl implements PlantService{

    private final PlantRepository plantRepository;
    private final PlantMapper plantMapper;
    private final UserRepository userRepository;

    public PlantServiceImpl(PlantRepository plantRepository, PlantMapper plantMapper, UserRepository userRepository) {
        this.plantRepository = plantRepository;
        this.plantMapper = plantMapper;
        this.userRepository = userRepository;
    }




    @Override
    public PlantResponse findById(Long id) {
        return this.plantRepository.findById(id)
                .map(this.plantMapper::toPlantResponse)
                //TODO: Manejar error
                .orElseThrow();
    }

    @Override
    public List<ListPlantResponse> findAllPlant() {
        return this.plantRepository.findAllPlants();
    }

    @Override
    public PlantResponse findByNamePlant(String namePlant) {
        Plant plant = this.plantRepository.findByName(namePlant)
                //TODO: Manejar Error
                .orElseThrow();
        return this.plantMapper.toPlantResponse(plant);
    }

    @Override
    public PlantResponse savePlant(Long id, PlantRequest plantRequest) {
        UserEntity user = this.userRepository.findById(id).orElseThrow();
        Plant plant = new Plant();
        plant.setName(plantRequest.getName());
        plant.setUser(user);
        plant.setCountry(plantRequest.getCountry());
        plant.setDate(ZonedDateTime.now());
        plant.setCheckups(0L);

     return this.plantMapper.toPlantResponse(this.plantRepository.save(plant));
    }

    @Override
    public PlantResponse updatePlant(Long id, PlantRequest plantRequest) {
        return this.plantRepository.findById(id)
                .map(plant -> {
                    plant.setName(plantRequest.getName());
                    plant.setCountry(plantRequest.getCountry());
                    return this.plantRepository.save(plant);
                })
                .map(plantMapper::toPlantResponse)
                .orElseThrow();
    }
}

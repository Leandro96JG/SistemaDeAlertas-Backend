package com.sistema_de_alertas.Sistema_de_Alertas.app.mappers;

import com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.response.PlantResponse;
import com.sistema_de_alertas.Sistema_de_Alertas.app.model.entity.Plant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AlertYellowMapper.class, AlertRedMapper.class, SensorDisableMapper.class, UserPlantMapper.class})
public interface PlantMapper {

PlantResponse toPlantResponse(Plant plant);

}

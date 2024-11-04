package com.sistema_de_alertas.Sistema_de_Alertas.app.mappers;

import com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.response.SensorsDisableResponse;
import com.sistema_de_alertas.Sistema_de_Alertas.app.model.entity.SensorDisable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SensorDisableMapper {
    SensorsDisableResponse toSensorDisableResponse(SensorDisable sensorDisable);
}

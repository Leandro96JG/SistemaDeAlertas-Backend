package com.sistema_de_alertas.Sistema_de_Alertas.app.mappers;

import com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.response.AlertYellowResponse;
import com.sistema_de_alertas.Sistema_de_Alertas.app.model.entity.AlertYellow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlertYellowMapper {
    AlertYellowResponse toAlertYellowResponse(AlertYellow alertYellow);
}

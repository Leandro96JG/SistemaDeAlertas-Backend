package com.sistema_de_alertas.Sistema_de_Alertas.app.mappers;

import com.sistema_de_alertas.Sistema_de_Alertas.app.model.dto.response.AlertRedResponse;
import com.sistema_de_alertas.Sistema_de_Alertas.app.model.entity.AlertRed;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlertRedMapper {

    AlertRedResponse toAlertRedResponse(AlertRed alertRed);

}

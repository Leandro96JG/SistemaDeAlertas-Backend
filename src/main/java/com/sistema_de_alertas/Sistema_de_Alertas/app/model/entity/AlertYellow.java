package com.sistema_de_alertas.Sistema_de_Alertas.app.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alerts_yellow")
public class AlertYellow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private ZonedDateTime date;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;
}

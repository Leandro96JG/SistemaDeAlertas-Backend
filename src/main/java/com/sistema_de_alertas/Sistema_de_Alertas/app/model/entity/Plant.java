package com.sistema_de_alertas.Sistema_de_Alertas.app.model.entity;

import com.sistema_de_alertas.Sistema_de_Alertas.auth.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    private Long checkups;

    private ZonedDateTime date;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AlertYellow> alertsYellow;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AlertRed> alertsRed;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SensorDisable> sensorsDisable;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}

package com.api.prueba.user.users.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;


@Entity
@Getter
@Setter
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Para generar el UUID automáticamente
    private Long id;

    private String number;
    private String cityCode;
    private String countryCode;
}

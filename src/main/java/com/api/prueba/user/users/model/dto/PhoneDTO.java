package com.api.prueba.user.users.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PhoneDTO {
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;
}

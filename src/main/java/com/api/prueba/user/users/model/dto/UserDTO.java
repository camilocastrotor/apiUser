package com.api.prueba.user.users.model.dto;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class UserDTO {

    private String id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
    private List<PhoneDTO> phones;
}

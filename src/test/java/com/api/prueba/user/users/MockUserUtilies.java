package com.api.prueba.user.users;

import com.api.prueba.user.users.model.dto.UserDTO;
import com.api.prueba.user.users.model.entity.User;

public class MockUserUtilies {



    public static UserDTO getUserDTO() {
        UserDTO userDTO =  new UserDTO();
        userDTO.setActive(true);
        userDTO.setName("Camilo");
        userDTO.setEmail("Camilo@gmail.com");
        userDTO.setPassword("Qwer441");

        return userDTO;
    }

    public static User getUser() {
        User user =  new User();
        user.setActive(true);
        user.setName("Camilo");
        user.setEmail("Camilo@gmail.com");
        user.setPassword("Qwer441");



        return user;
    }
}

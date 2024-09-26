package com.api.prueba.user.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ResourceBundle;

@Configuration
public class BeanConfig {

    @Bean
    public ResourceBundle messageResource() {
        return ResourceBundle.getBundle("messages");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

package com.api.prueba.user.users.services;

import com.api.prueba.user.common.util.UtilitiesExecuteRule;
import com.api.prueba.user.users.model.dto.UserDTO;
import com.api.prueba.user.users.model.entity.User;
import com.api.prueba.user.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  ModelMapper modelMapper;



    public UserDTO createUsuario(UserDTO usuarioDto) {
        this.validate(usuarioDto);
        User usuario = modelMapper.map(usuarioDto, User.class);
        Optional<User> existingUser = userRepository.findByEmail(usuario.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }
        usuario.setCreated(LocalDateTime.now());
        usuario.setModified(LocalDateTime.now());
        usuario.setLastLogin(LocalDateTime.now());
        usuario.setToken(generateToken(usuario.getName()));
        usuario.setActive(true);

        User usuarioResponse= userRepository.save(usuario);
        return modelMapper.map(usuarioResponse, UserDTO.class);

    }

    private String generateToken(String name) {
        return UtilitiesExecuteRule.generateToken(name);
    }

    private void  validate(UserDTO usuarioDto) {
        if(!UtilitiesExecuteRule.isValidEmail(usuarioDto.getEmail())){
            throw new IllegalArgumentException("El correo invalido");

        }
        if(!UtilitiesExecuteRule.isValidPass(usuarioDto.getPassword())){
            throw new IllegalArgumentException("Contraseña invalida");
        }

    }


}

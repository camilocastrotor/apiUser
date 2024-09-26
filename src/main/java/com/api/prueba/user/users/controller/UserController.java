package com.api.prueba.user.users.controller;

import com.api.prueba.user.common.model.DataHeader;
import com.api.prueba.user.common.model.ErrorDTO;
import com.api.prueba.user.common.model.ResponseDTO;
import com.api.prueba.user.users.model.dto.UserDTO;
import com.api.prueba.user.users.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH})
@Tag(name = "/v1/user", description = "Group of operations for user")
public class UserController {

    private final UserService usuarioService;

    public UserController(UserService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDTO<UserDTO>> saveUser(@RequestBody UserDTO usuario ){
        DataHeader dataHeader = new DataHeader();
        ResponseDTO<UserDTO> response;

        List<ErrorDTO> errors = new ArrayList<>();

        try {
            UserDTO createdUsuario = usuarioService.createUsuario(usuario);
            response = new ResponseDTO<>(createdUsuario, dataHeader);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            String codeNameError = e.getMessage();

            errors.add( new ErrorDTO(String.valueOf(400), e.getMessage()));
            dataHeader = new DataHeader(400, codeNameError, errors);
            response = new ResponseDTO<>(null, dataHeader);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

}

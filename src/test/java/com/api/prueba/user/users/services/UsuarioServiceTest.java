package com.api.prueba.user.users.services;

import com.api.prueba.user.common.util.UtilitiesExecuteRule;
import com.api.prueba.user.users.model.entity.User;
import com.api.prueba.user.users.repository.UserRepository;
import com.api.prueba.user.users.MockUserUtilies;
import com.api.prueba.user.users.model.dto.UserDTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UserServiceImpl usuarioService;

    @Mock
    UserRepository usuarioRepository;

    @Mock
    private ModelMapper modelMapper;

    private MockedStatic<UtilitiesExecuteRule> utilitiesExecuteRule;

    @BeforeEach
    void setUp() {

        utilitiesExecuteRule = mockStatic(UtilitiesExecuteRule.class);
    }
    @AfterEach
    void afterTest() {
        utilitiesExecuteRule.close();
    }

    @Test
    void saveOk()  {
        User user = MockUserUtilies.getUser();
        when(usuarioRepository.save(user)).thenReturn(user);
        User responseDTO = usuarioRepository.save(user);
        assertNotNull(responseDTO);
    }

    @Test
    void shouldThrowExceptionWhenEmailExist() {
        User user = MockUserUtilies.getUser();
        UserDTO userDTO = MockUserUtilies.getUserDTO();

        when(modelMapper.map(eq(userDTO), eq(User.class))).thenReturn(user);
        when(usuarioRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        utilitiesExecuteRule.when(
                () -> UtilitiesExecuteRule.isValidPass(anyString())
        ).thenReturn(true);
        utilitiesExecuteRule.when(
                () -> UtilitiesExecuteRule.isValidEmail(anyString())
        ).thenReturn(true);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.createUsuario(userDTO);
        });

        assertEquals("El correo ya está registrado", exception.getMessage());
    }


    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("correo_invalido");
        utilitiesExecuteRule.when(
                () -> UtilitiesExecuteRule.isValidPass(anyString())
        ).thenReturn(false);
        utilitiesExecuteRule.when(
                () -> UtilitiesExecuteRule.isValidEmail(anyString())
        ).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.createUsuario(userDTO);
        });


       assertEquals("El correo invalido", exception.getMessage());

        verify(modelMapper, never()).map(any(), any());
    }
    @Test
    void shouldThrowExceptionWhenPassIsInvalid() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("1");
        userDTO.setEmail("camilo@gmail.com");
        utilitiesExecuteRule.when(
                () -> UtilitiesExecuteRule.isValidPass(anyString())
        ).thenReturn(false);
        utilitiesExecuteRule.when(
                () -> UtilitiesExecuteRule.isValidEmail(anyString())
        ).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.createUsuario(userDTO);
        });

        assertEquals("Contraseña invalida", exception.getMessage());

        verify(modelMapper, never()).map(any(), any());
    }

}



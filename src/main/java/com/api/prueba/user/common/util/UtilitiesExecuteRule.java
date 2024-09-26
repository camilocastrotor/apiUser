package com.api.prueba.user.common.util;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.regex.Pattern;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Data
@Component
public class UtilitiesExecuteRule {

    @Value("${user.passregex}")
    private String PASSREGEX;

    // Expresión regular para validar el formato del correo electrónico
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private static Pattern PASSPATTERN;

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public UtilitiesExecuteRule() {
        // Do nothing
    }

    @PostConstruct
    public void init() {
        if (PASSREGEX != null && !PASSREGEX.isEmpty()) {
            this.PASSPATTERN = Pattern.compile(PASSREGEX);
        } else {
            throw new IllegalArgumentException("El patrón de la contraseña no puede ser nulo o vacío.");
        }
    }


    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPass(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return PASSPATTERN.matcher(password).matches();
    }

    public static String generateToken(String username) {
        long expirationTime = 3600000; // 1 hora en milisegundos
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY)
                .compact();
    }
}

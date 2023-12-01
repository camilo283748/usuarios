package com.bci.usuarios.controller;

import com.bci.usuarios.models.entidades.Usuario;
import com.bci.usuarios.seguridad.jwt.AuthService;
import com.bci.usuarios.seguridad.jwt.util.AuthTokenResponse;
import com.bci.usuarios.seguridad.jwt.util.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request)
    {
        AuthTokenResponse authTokenResponse = null;
        Map<String, Object> response = new HashMap<>();
        try{
            authTokenResponse= authService.login(request);
        }catch (BadCredentialsException e){
            response.put("mensaje",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("token",authTokenResponse.getToken());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/registrar")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Usuario usuario)
    {
        usuario.setId(null);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        Map<String, Object> response = new HashMap<>();
        try {

            usuario = authService.register(usuario);
        }catch (DataAccessException e){
            response.put("mensaje", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        response.put("id",usuario.getId());
        response.put("creado",usuario.getCreado());
        response.put("modificado",usuario.getModificado());
        response.put("ultimoLogin",usuario.getUltimoLogin());
        response.put("token", usuario.getToken());
        response.put("activo",usuario.getActivo());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

package com.bci.usuarios.seguridad.jwt;

import com.bci.usuarios.models.dao.IRepoUsuario;
import com.bci.usuarios.models.entidades.Usuario;
import com.bci.usuarios.seguridad.jwt.util.AuthTokenResponse;
import com.bci.usuarios.seguridad.jwt.util.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final IRepoUsuario iRepoUsuario;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthTokenResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        Usuario user = iRepoUsuario.findByUsername(request.getUsername()).orElseThrow();
        user.setUltimoLogin(new Date());
        String token = jwtService.getToken(user);
        user.setToken(token);
        user.setModificado(new Date());
        iRepoUsuario.save(user);
        return AuthTokenResponse.builder()
                .token(token)
                .build();

    }

    public Usuario register(Usuario usuario) {
        AuthTokenResponse token = AuthTokenResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
        usuario.setToken(token.getToken());
        iRepoUsuario.save(usuario);
        return usuario;
    }

}

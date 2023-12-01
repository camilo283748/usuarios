package com.bci.usuarios.controller;

import com.bci.usuarios.models.entidades.Usuario;
import com.bci.usuarios.models.servicio.UsuarioService;
import com.bci.usuarios.util.Constantes;
import jakarta.validation.Valid;
import org.apache.tomcat.util.bcel.Const;
import org.h2.schema.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = {Constantes.USUARIO_ENDPOINT}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object listarUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping(value = {Constantes.USUARIO_ENDPOINT+"/{id}"}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> buscarUsuarioId(@PathVariable Long id) {
        Usuario usuario = null;
        Map<String, Object> response = new HashMap<>();

        try {
            usuario = usuarioService.findById(id);
        } catch (DataAccessException e) {
            response.put(Constantes.MENSAJE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("usuario", usuario);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping(Constantes.USUARIO_ENDPOINT)
    public ResponseEntity<Map<String, Object>> actualizarUsuario(@Valid @RequestBody Usuario usuario) {

        Map<String, Object> response = new HashMap<>();

        try {

            Usuario usuarioExiste = usuarioService.findById(usuario.getId());
            usuario.setModificado(new Date());
            usuario.setToken(usuarioExiste.getToken());
            usuario.setCreado(usuarioExiste.getCreado());
            usuarioService.save(usuario);
        } catch (DataAccessException e) {
            response.put(Constantes.MENSAJE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put(Constantes.MENSAJE, "El usuario ha sido modificado con éxito!");
        response.put("usuario", usuario);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @DeleteMapping(Constantes.USUARIO_ENDPOINT+"/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Usuario usuario = usuarioService.findById(id);
            if (usuario == null) {
                response.put(Constantes.MENSAJE, "El Usuario no existe en la base de datos");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            usuarioService.delete(id);
        } catch (DataAccessException e) {
            response.put(Constantes.MENSAJE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put(Constantes.MENSAJE, "El usuario ha sido eliminado con éxito!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

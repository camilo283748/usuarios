package com.bci.usuarios.models.servicio;

import com.bci.usuarios.models.entidades.Usuario;

import java.util.List;

public interface UsuarioService {

    public List<Usuario> findAll();

    public Usuario findById(Long id);

    public Usuario save(Usuario usuario);

    public void delete(Long id);

}

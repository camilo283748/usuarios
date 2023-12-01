package com.bci.usuarios.models.dao;

import com.bci.usuarios.models.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRepoUsuario extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}

package com.bci.usuarios.models.dao;

import com.bci.usuarios.models.entidades.Telefono;
import org.springframework.data.repository.CrudRepository;

public interface IRepoTelefono extends CrudRepository<Telefono, Long> {
}

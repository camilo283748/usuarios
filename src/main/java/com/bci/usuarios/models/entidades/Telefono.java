package com.bci.usuarios.models.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name="TELEFONOS")
@Data
public class Telefono implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int numero;
    @Column
    private int codigoCiudad;
    @Column
    private int codigoPais;

}

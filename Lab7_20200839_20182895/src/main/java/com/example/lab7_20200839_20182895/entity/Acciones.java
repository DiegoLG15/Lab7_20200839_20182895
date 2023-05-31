package com.example.lab7_20200839_20182895.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "acciones")
public class Acciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "fecha")
    private Instant fecha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuarios_id", nullable = false)
    private Usuario usuarios;

}
package com.example.lab7_20200839_20182895.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cuotas")
public class Cuota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "numero_cuota")
    private Integer numeroCuota;

    @Column(name = "monto")
    private Double monto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creditos_id", nullable = false)
    private Credito creditos;

}
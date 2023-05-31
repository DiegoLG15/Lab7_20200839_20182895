package com.example.lab7_20200839_20182895.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "o, c.fin, c.estado",
            nativeQuery = true) //TENER CUIDADO CON El PUNTO Y COMA AL FINAL DEL QUERY PQ SINO, NO FUNCIONA
    List<Usuario> listarPorDoctorListaPacientes(String id);
}

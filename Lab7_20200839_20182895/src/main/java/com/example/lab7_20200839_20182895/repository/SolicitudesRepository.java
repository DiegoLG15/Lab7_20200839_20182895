package com.example.lab7_20200839_20182895.repository;


import com.example.lab7_20200839_20182895.entity.Solicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudesRepository extends JpaRepository<Solicitudes, Integer> {
}

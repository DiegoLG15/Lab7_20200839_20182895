package com.example.lab7_20200839_20182895.repository;


import com.example.lab7_20200839_20182895.entity.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagosRespository extends JpaRepository<Pagos, Integer> {
}

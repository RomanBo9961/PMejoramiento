package com.pm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pm.demo.model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}

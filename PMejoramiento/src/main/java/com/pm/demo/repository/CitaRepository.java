package com.pm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pm.demo.model.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
}

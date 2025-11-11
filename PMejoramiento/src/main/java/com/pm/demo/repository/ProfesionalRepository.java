package com.pm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pm.demo.model.Profesional;

public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {
}

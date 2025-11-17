package com.pm.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.demo.model.Profesional;
import com.pm.demo.repository.ProfesionalRepository;

@Service
public class ProfesionalService {

	@Autowired
	private ProfesionalRepository profesionalRepository;

	public List<Profesional> findAll() {
		return profesionalRepository.findAll();
	}

	public Profesional save(Profesional profesional) {
		return profesionalRepository.save(profesional);
	}

	public Profesional findById(Integer id) {
		return profesionalRepository.findById(id).orElse(null);
	}

	public void deleteById(Integer id) {
		profesionalRepository.deleteById(id);
	}
}

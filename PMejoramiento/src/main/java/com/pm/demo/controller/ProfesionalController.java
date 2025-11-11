package com.pm.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pm.demo.model.Profesional;
import com.pm.demo.repository.ProfesionalRepository;

@Controller
@RequestMapping("/profesionales")
public class ProfesionalController {

	private final ProfesionalRepository repo;

	public ProfesionalController(ProfesionalRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("profesionales", repo.findAll());
		model.addAttribute("nuevoProfesional", new Profesional());
		return "index";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Profesional profesional) {
		repo.save(profesional);
		return "redirect:/profesionales";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		model.addAttribute("profesional", repo.findById(id).orElse(null));
		return "profesionales/editar";
	}

	@PostMapping("/actualizar/{id}")
	public String actualizar(@PathVariable Integer id, @ModelAttribute Profesional profesional) {
		profesional.setId(id);
		repo.save(profesional);
		return "redirect:/profesionales";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {
		repo.deleteById(id);
		return "redirect:/profesionales";
	}
}
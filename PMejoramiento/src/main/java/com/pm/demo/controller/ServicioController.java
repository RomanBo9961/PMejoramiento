package com.pm.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pm.demo.model.Servicio;
import com.pm.demo.repository.ServicioRepository;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

	private final ServicioRepository repo;

	public ServicioController(ServicioRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("servicios", repo.findAll());
		model.addAttribute("nuevoServicio", new Servicio());
		return "servicios/lista";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Servicio servicio) {
		repo.save(servicio);
		return "redirect:/servicios";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		model.addAttribute("servicio", repo.findById(id).orElse(null));
		return "servicios/editar";
	}

	@PostMapping("/actualizar/{id}")
	public String actualizar(@PathVariable Integer id, @ModelAttribute Servicio servicio) {
		servicio.setId(id);
		repo.save(servicio);
		return "redirect:/servicios";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {
		repo.deleteById(id);
		return "redirect:/servicios";
	}
}

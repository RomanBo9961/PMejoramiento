package com.pm.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pm.demo.model.Cita;
import com.pm.demo.repository.CitaRepository;
import com.pm.demo.repository.UsuarioRepository;
import com.pm.demo.repository.ServicioRepository;
import com.pm.demo.repository.ProfesionalRepository;

@Controller
@RequestMapping("/citas")
public class CitaController {

	private final CitaRepository citaRepo;
	private final UsuarioRepository usuarioRepo;
	private final ServicioRepository servicioRepo;
	private final ProfesionalRepository profesionalRepo;

	public CitaController(CitaRepository citaRepo, UsuarioRepository usuarioRepo, ServicioRepository servicioRepo,
			ProfesionalRepository profesionalRepo) {
		this.citaRepo = citaRepo;
		this.usuarioRepo = usuarioRepo;
		this.servicioRepo = servicioRepo;
		this.profesionalRepo = profesionalRepo;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("citas", citaRepo.findAll());
		model.addAttribute("nuevaCita", new Cita());
		model.addAttribute("usuarios", usuarioRepo.findAll());
		model.addAttribute("servicios", servicioRepo.findAll());
		model.addAttribute("profesionales", profesionalRepo.findAll());
		return "citas/lista";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Cita cita) {
		citaRepo.save(cita);
		return "redirect:/citas";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		model.addAttribute("cita", citaRepo.findById(id).orElse(null));
		model.addAttribute("usuarios", usuarioRepo.findAll());
		model.addAttribute("servicios", servicioRepo.findAll());
		model.addAttribute("profesionales", profesionalRepo.findAll());
		return "citas/editar";
	}

	@PostMapping("/actualizar/{id}")
	public String actualizar(@PathVariable Integer id, @ModelAttribute Cita cita) {
		cita.setId(id);
		citaRepo.save(cita);
		return "redirect:/citas";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {
		citaRepo.deleteById(id);
		return "redirect:/citas";
	}
}
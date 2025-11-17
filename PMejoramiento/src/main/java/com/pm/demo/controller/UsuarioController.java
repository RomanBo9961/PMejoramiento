package com.pm.demo.controller;

import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pm.demo.model.Usuario;
import com.pm.demo.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	private final UsuarioRepository repo;

	public UsuarioController(UsuarioRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/registro")
	public String mostrarFormularioRegistro(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuarios/registro"; // 🔥 ¡CORREGIDO!
	}

	@PostMapping("/registro")
	public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {

		// Verificar si el email ya existe
		if (repo.findByEmail(usuario.getEmail()).isPresent()) {
			model.addAttribute("error", "El email ya está registrado");
			return "usuarios/registro"; // 🔥 ¡CORREGIDO!
		}

		// Encripta CONTRASEÑA y guarda
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		usuario.setFechaRegistro(LocalDateTime.now());

		repo.save(usuario);

		return "redirect:/login?registroExitoso";
	}

	@GetMapping("/usuarios")
	public String listar(Model model) {
		model.addAttribute("usuarios", repo.findAll());
		model.addAttribute("nuevoUsuario", new Usuario());
		return "usuarios/lista";
	}

	@PostMapping("/usuarios/guardar")
	public String guardar(@ModelAttribute Usuario usuario) {
		repo.save(usuario);
		return "redirect:/usuarios";
	}

	@GetMapping("/usuarios/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		model.addAttribute("usuario", repo.findById(id).orElse(null));
		return "usuarios/editar";
	}

	@PostMapping("/usuarios/actualizar")
	public String actualizar(@ModelAttribute Usuario usuario) {
		repo.save(usuario);
		return "redirect:/usuarios";
	}

	@GetMapping("/usuarios/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {
		repo.deleteById(id);
		return "redirect:/usuarios";
	}
}
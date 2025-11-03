package controller;

import model.Usuario;
import repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioRepository repo;

	public UsuarioController(UsuarioRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("usuarios", repo.findAll());
		model.addAttribute("nuevoUsuario", new Usuario());
		return "usuarios/lista";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Usuario usuario) {
		repo.save(usuario);
		return "redirect:/usuarios";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		model.addAttribute("usuario", repo.findById(id).orElse(null));
		return "usuarios/editar";
	}

	@PostMapping("/actualizar/{id}")
	public String actualizar(@PathVariable Integer id, @ModelAttribute Usuario usuario) {
		usuario.setId(id);
		repo.save(usuario);
		return "redirect:/usuarios";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {
		repo.deleteById(id);
		return "redirect:/usuarios";
	}
}

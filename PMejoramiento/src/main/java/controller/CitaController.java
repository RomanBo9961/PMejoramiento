package controller;

import model.Cita;
import repository.CitaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/citas")
public class CitaController {

	private final CitaRepository repo;

	public CitaController(CitaRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("citas", repo.findAll());
		model.addAttribute("nuevaCita", new Cita());
		return "citas/lista";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Cita cita) {
		repo.save(cita);
		return "redirect:/citas";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		model.addAttribute("cita", repo.findById(id).orElse(null));
		return "citas/editar";
	}

	@PostMapping("/actualizar/{id}")
	public String actualizar(@PathVariable Integer id, @ModelAttribute Cita cita) {
		cita.setId(id);
		repo.save(cita);
		return "redirect:/citas";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {
		repo.deleteById(id);
		return "redirect:/citas";
	}
}

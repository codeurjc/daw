package es.codeurjc.web;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionController {

	private String infoCompartida;

	@PostMapping("/procesarFormulario")
	public String procesarFormulario(@RequestParam String info, HttpSession session) {

		session.setAttribute("infoUsuario", info);
		infoCompartida = info;

		return "resultado_formulario";
	}

	@GetMapping("/mostrarDatos")
	public String mostrarDatos(Model model, HttpSession session) {

		String infoUsuario = (String) session.getAttribute("infoUsuario");

		model.addAttribute("infoUsuario", infoUsuario);
		model.addAttribute("infoCompartida", infoCompartida);

		return "datos";
	}
}

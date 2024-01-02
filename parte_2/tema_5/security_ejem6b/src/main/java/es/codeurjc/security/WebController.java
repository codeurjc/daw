package es.codeurjc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class WebController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/loginerror")
	public String loginerror() {
		return "loginerror";
	}

	@GetMapping("/private")
	public String privatePage(Model model, HttpServletRequest request) {

		String name = request.getUserPrincipal().getName();
		
		User user = userRepository.findByName(name).orElseThrow();

		model.addAttribute("username", user.getName());		
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		
		return "private";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
}

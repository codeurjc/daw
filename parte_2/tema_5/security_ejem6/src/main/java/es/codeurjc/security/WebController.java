package es.codeurjc.security;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
	    model.addAttribute("token", token.getToken());   
		
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
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
	    model.addAttribute("token", token.getToken());   

		return "private";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
}

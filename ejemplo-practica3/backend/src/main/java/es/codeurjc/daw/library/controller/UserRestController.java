package es.codeurjc.daw.library.controller;

import java.security.Principal;
import java.util.NoSuchElementException;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.library.dto.UserDTO;
import es.codeurjc.daw.library.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/me")
	public UserDTO me(HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		
		if(principal != null) {
			return userService.getUser(principal.getName());
		} else {
			throw new NoSuchElementException();
		}
	}
}

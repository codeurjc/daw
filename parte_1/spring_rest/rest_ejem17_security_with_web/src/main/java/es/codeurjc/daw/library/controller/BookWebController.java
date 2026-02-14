package es.codeurjc.daw.library.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.daw.library.model.Book;
import es.codeurjc.daw.library.service.BookService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BookWebController {

	@Autowired
	private BookService service;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if(principal != null) {
		
			model.addAttribute("logged", true);		
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			
		} else {
			model.addAttribute("logged", false);
		}
	}

	@GetMapping("/")
	public String showBooks(Model model) {

		model.addAttribute("books", service.findAll());

		return "books";
	}

	@GetMapping("/books/{id}")
	public String showBook(Model model, @PathVariable long id) {

		Optional<Book> book = service.findById(id);
		if (book.isPresent()) {
			model.addAttribute("book", book.get());
			return "book";
		} else {
			return "redirect:/";
		}

	}

	@PostMapping("/removebook/{id}")
	public String removeBook(Model model, @PathVariable long id) {

		Optional<Book> book = service.findById(id);
		if (book.isPresent()) {
			service.delete(id);
			model.addAttribute("book", book.get());
			return "removedbook";
		}else{
			return "redirect:/";
		}
	}

	@GetMapping("/newbook")
	public String newBook(Model model) {
		return "newBookPage";
	}

	@PostMapping("/newbook")
	public String newBookProcess(Model model, Book book) {

		service.save(book);
		
		model.addAttribute("bookId",book.getId());

		return "bookCreated";
	}

	@GetMapping("/editbook/{id}")
	public String editBook(Model model, @PathVariable long id) {

		Optional<Book> book = service.findById(id);
		if (book.isPresent()) {
			model.addAttribute("book", book.get());
			return "editBookPage";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/editbook")
	public String editBookProcess(Model model, Book book) {

		service.save(book);
		
		model.addAttribute("bookId",book.getId());

		return "bookEdited";
	}

}

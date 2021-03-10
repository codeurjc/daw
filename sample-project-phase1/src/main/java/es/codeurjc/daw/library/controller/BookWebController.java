package es.codeurjc.daw.library.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.daw.library.model.Book;
import es.codeurjc.daw.library.service.BookService;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BookWebController {

	@Autowired
	private BookService service;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

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
			return "books";
		}

	}

	@GetMapping("/books/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Optional<Book> book = service.findById(id);
		if (book.isPresent() && book.get().getImageFile() != null) {

			Resource file = new InputStreamResource(book.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(book.get().getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/removebook/{id}")
	public String removeBook(Model model, @PathVariable long id) {

		Optional<Book> book = service.findById(id);
		if (book.isPresent()) {
			service.delete(id);
			model.addAttribute("book", book.get());
		}
		return "removedbook";
	}

	@GetMapping("/newbook")
	public String newBook(Model model) {

		return "newBookPage";
	}

	@PostMapping("/newbook")
	public String newBookProcess(Model model, Book book, MultipartFile imageField) throws IOException {

		if (!imageField.isEmpty()) {
			book.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			book.setImage(true);
		}

		service.save(book);

		model.addAttribute("bookId", book.getId());

		return "redirect:/books/"+book.getId();
	}

	@GetMapping("/editbook/{id}")
	public String editBook(Model model, @PathVariable long id) {

		Optional<Book> book = service.findById(id);
		if (book.isPresent()) {
			model.addAttribute("book", book.get());
			return "editBookPage";
		} else {
			return "books";
		}
	}

	@PostMapping("/editbook")
	public String editBookProcess(Model model, Book book, boolean removeImage, MultipartFile imageField)
			throws IOException, SQLException {

		updateImage(book, removeImage, imageField);

		service.save(book);

		model.addAttribute("bookId", book.getId());

		return "redirect:/books/"+book.getId();
	}

	private void updateImage(Book book, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		
		if (!imageField.isEmpty()) {
			book.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			book.setImage(true);
		} else {
			if (removeImage) {
				book.setImageFile(null);
				book.setImage(false);
			} else {
				// Maintain the same image loading it before updating the book
				Book dbBook = service.findById(book.getId()).orElseThrow();
				if (dbBook.hasImage()) {
					book.setImageFile(BlobProxy.generateProxy(dbBook.getImageFile().getBinaryStream(),
							dbBook.getImageFile().length()));
					book.setImage(true);
				}
			}
		}
	}

}

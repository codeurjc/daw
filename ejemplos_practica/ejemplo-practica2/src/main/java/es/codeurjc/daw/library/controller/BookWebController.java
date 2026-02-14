package es.codeurjc.daw.library.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.daw.library.model.Book;
import es.codeurjc.daw.library.model.Image;
import es.codeurjc.daw.library.service.BookService;
import es.codeurjc.daw.library.service.ImageService;
import es.codeurjc.daw.library.service.ShopService;

@Controller
public class BookWebController {

	@Autowired
	private BookService bookService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private ImageService imageService;

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

		model.addAttribute("books", bookService.getBooks());

		return "books";
	}

	@GetMapping("/books/{id}")
	public String showBook(Model model, @PathVariable long id) {

		Book book = bookService.getBook(id);
		if (book != null) {
			model.addAttribute("book", book);
			return "book";
		} else {
			return "books";
		}

	}

	@PostMapping("/removebook/{id}")
	public String removeBook(Model model, @PathVariable long id) {

		Book book = bookService.getBook(id);
		if (book != null) {
			bookService.deleteBook(id);
			model.addAttribute("book", book);
		}
		return "removedbook";
	}

	@GetMapping("/newbook")
	public String newBook(Model model) {

		model.addAttribute("availableShops", shopService.getShops());

		return "newBookPage";
	}

	@PostMapping("/newbook")
	public String newBookProcess(Model model, Book book, MultipartFile imageField,
			@RequestParam List<Long> selectedShops) throws IOException {

		if (!imageField.isEmpty()) {
			Image image = imageService.createImage(imageField.getInputStream());
			book.setImage(image);
		}

		book.setShops(shopService.getShops(selectedShops));

		bookService.createBook(book);

		model.addAttribute("bookId", book.getId());

		return "redirect:/books/" + book.getId();
	}

	@GetMapping("/editbook/{id}")
	public String editBook(Model model, @PathVariable long id) {

		Book book = bookService.getBook(id);
		if (book != null) {
			model.addAttribute("book", book);
			return "editBookPage";
		} else {
			return "books";
		}
	}

	@PostMapping("/editbook")
	public String editBookProcess(Model model, Book book, boolean removeImage, MultipartFile imageField)
			throws IOException, SQLException {

		updateImage(book, removeImage, imageField);

		bookService.replaceBook(book.getId(), book);

		model.addAttribute("bookId", book.getId());

		return "redirect:/books/" + book.getId();
	}

	private void updateImage(Book book, boolean removeImage, MultipartFile imageField)
			throws IOException, SQLException {

		if (!imageField.isEmpty()) {
			Book dbBook = bookService.getBook(book.getId());

			if (dbBook.getImage() == null) {
				Image image = imageService.createImage(imageField.getInputStream());
				bookService.addImageToBook(book.getId(), image);
			} else {
				Image image = imageService.replaceImageFile(dbBook.getImage().getId(),
						imageField.getInputStream());

				bookService.addImageToBook(book.getId(), image);
			}
		} else {
			if (removeImage) {
				if (book.getImage() != null) {
					imageService.deleteImage(book.getImage().getId());
					bookService.removeImageFromBook(book.getId());
				}
			} else {
				// Maintain the same image loading it before updating the book
				Book dbBook = bookService.getBook(book.getId());
				bookService.addImageToBook(book.getId(), dbBook.getImage());
			}
		}
	}

}

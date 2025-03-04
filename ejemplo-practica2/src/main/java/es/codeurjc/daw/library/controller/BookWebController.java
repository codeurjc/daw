package es.codeurjc.daw.library.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.daw.library.dto.BookDTO;
import es.codeurjc.daw.library.dto.NewBookRequestDTO;
import es.codeurjc.daw.library.dto.ShopBasicDTO;
import es.codeurjc.daw.library.service.BookService;
import es.codeurjc.daw.library.service.ShopService;

@Controller
public class BookWebController {

	@Autowired
	private BookService bookService;

	@Autowired
	private ShopService shopService;

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

		try {

			BookDTO bookDTO = bookService.getBook(id);
			model.addAttribute("book", bookDTO);

			return "book";

		} catch (NoSuchElementException e) {
			return "bookNotFound";
		}
	}

	@GetMapping("/removebook/{id}")
	public String removeBook(Model model, @PathVariable long id) {

		try {
			BookDTO bookDTO = bookService.deleteBook(id);
			model.addAttribute("book", bookDTO);

			return "removedBook";

		} catch (NoSuchElementException e) {
			return "bookNotFound";
		}
	}

	@GetMapping("/newbook")
	public String newBook(Model model) {

		model.addAttribute("availableShops", shopService.findAll());

		return "newBookPage";
	}	

	@GetMapping("/editbook/{id}")
	public String editBook(Model model, @PathVariable long id) {

		try {

			BookDTO bookDTO = bookService.getBook(id);
			model.addAttribute("book", bookDTO);

			List<SelectedShop> selectedShops = getSelectedShops(bookDTO);
			model.addAttribute("selectedShops", selectedShops);

			return "editBookPage";

		} catch (NoSuchElementException e) {
			return "bookNotFound";
		}
	}

	private record SelectedShop(ShopBasicDTO shop, boolean selected) {
	}

	private List<SelectedShop> getSelectedShops(BookDTO bookDTO) {
		
		Set<Long> selectedShops = bookDTO.shops().stream().map(ShopBasicDTO::id).collect(Collectors.toSet());

		List<SelectedShop> shopsWithSelected = shopService.findAll().stream()
				.map(shop -> new SelectedShop(shop, selectedShops.contains(shop.id())))
				.toList();

		return shopsWithSelected;
	}

	@PostMapping("/editbook")
	public String editBookProcess(Model model, NewBookRequestDTO newBookRequestDTO, long bookId, boolean removeImage) throws IOException, SQLException {

		createOrReplaceBook(newBookRequestDTO, bookId, removeImage);

		return "redirect:/books/" + bookId;
	}

	@PostMapping("/newbook")
	public String newBookProcess(Model model, NewBookRequestDTO newBookRequestDTO) throws IOException, SQLException {

		BookDTO newBookDTO = createOrReplaceBook(newBookRequestDTO, null, null);

		return "redirect:/books/" + newBookDTO.id();
	}

	private BookDTO createOrReplaceBook(NewBookRequestDTO newBookRequestDTO, Long bookId, Boolean removeImage)
			throws SQLException, IOException {

		boolean	image = false;
		if(bookId != null) {
			BookDTO oldBook = bookService.getBook(bookId);
			image = removeImage ? false : oldBook.image();
		}

		List<ShopBasicDTO> shopDTOs = Collections.emptyList();
		if(newBookRequestDTO.shops() != null) {
			shopDTOs = newBookRequestDTO.shops()
			.stream().map(id -> new ShopBasicDTO(id, null, null)).toList();
		}		

		BookDTO bookDTO = new BookDTO(bookId,
			newBookRequestDTO.title(), newBookRequestDTO.description(), image, shopDTOs);
				
		BookDTO newBookDTO = bookService.createOrReplaceBook(bookId, bookDTO);

		MultipartFile imageField = newBookRequestDTO.imageField();
		if (!imageField.isEmpty()) {
			bookService.createBookImage(bookDTO.id(), imageField.getInputStream(), imageField.getSize());
		}

		return newBookDTO;
	}

	@GetMapping("/books/{id}/image")
	public ResponseEntity<Object> getBookImage(@PathVariable long id) throws SQLException, IOException {

		Resource bookImage = bookService.getBookImage(id);

		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
				.body(bookImage);

	}

}

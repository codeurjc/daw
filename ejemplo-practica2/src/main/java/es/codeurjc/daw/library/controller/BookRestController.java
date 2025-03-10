package es.codeurjc.daw.library.controller;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.daw.library.dto.BookDTO;
import es.codeurjc.daw.library.service.BookService;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

	@Autowired
	private BookService bookService;

	@GetMapping("/")
	public Collection<BookDTO> getBooks() {

		return bookService.getBooks();
	}

	@GetMapping("/{id}")
	public BookDTO getBook(@PathVariable long id) {

		return bookService.getBook(id);
	}

	@PostMapping("/")
	public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {

		bookDTO = bookService.createBook(bookDTO);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(bookDTO.id()).toUri();

		return ResponseEntity.created(location).body(bookDTO);
	}

	@PutMapping("/{id}")
	public BookDTO replaceBook(@PathVariable long id, @RequestBody BookDTO updatedBookDTO) throws SQLException {

		return bookService.replaceBook(id, updatedBookDTO);
	}

	@DeleteMapping("/{id}")
	public BookDTO deleteBook(@PathVariable long id) {

		return bookService.deleteBook(id);
	}

	@PostMapping("/{id}/image")
	public ResponseEntity<Object> createBookImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
			throws IOException {

		bookService.createBookImage(id, imageFile.getInputStream(), imageFile.getSize());

		URI location = fromCurrentRequest().build().toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<Object> getBookImage(@PathVariable long id) throws SQLException, IOException {

		Resource postImage = bookService.getBookImage(id);

		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
				.body(postImage);

	}

	@PutMapping("/{id}/image")
	public ResponseEntity<Object> replaceBookImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
			throws IOException {

		bookService.replaceBookImage(id, imageFile.getInputStream(), imageFile.getSize());

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}/image")
	public ResponseEntity<Object> deleteBookImage(@PathVariable long id) throws IOException {

		bookService.deleteBookImage(id);

		return ResponseEntity.noContent().build();
	}
}

package es.codeurjc.daw.library.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.library.model.Book;
import es.codeurjc.daw.library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

	@Autowired
	private BookService service;

	@GetMapping("/")
	public Collection<Book> getBooks() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable long id) {

		Optional<Book> op = service.findById(id);
		if (op.isPresent()) {
			Book book = op.get();
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Book createBook(@RequestBody Book book) {

		service.save(book);

		return book;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book updatedBook) {

		if (service.exist(id)) {

			updatedBook.setId(id);
			service.save(updatedBook);

			return new ResponseEntity<>(updatedBook, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable long id) {

		try {
			service.delete(id);
			return new ResponseEntity<>(null, HttpStatus.OK);

		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}

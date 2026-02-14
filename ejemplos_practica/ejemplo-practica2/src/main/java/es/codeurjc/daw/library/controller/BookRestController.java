package es.codeurjc.daw.library.controller;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
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
import es.codeurjc.daw.library.dto.BookMapper;
import es.codeurjc.daw.library.dto.ImageDTO;
import es.codeurjc.daw.library.dto.ImageMapper;
import es.codeurjc.daw.library.model.Book;
import es.codeurjc.daw.library.model.Image;
import es.codeurjc.daw.library.service.BookService;
import es.codeurjc.daw.library.service.ImageService;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

	@Autowired
	private BookService bookService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private BookMapper bookMapper;

	@Autowired
	private ImageMapper imageMapper;

	@GetMapping("/")
	public Collection<BookDTO> getBooks() {

		return bookMapper.toDTOs(bookService.getBooks());
	}

	@GetMapping("/{id}")
	public BookDTO getBook(@PathVariable long id) {

		return bookMapper.toDTO(bookService.getBook(id));
	}

	@PostMapping("/")
	public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {

		Book book = bookMapper.toDomain(bookDTO);
		book = bookService.createBook(book);
		bookDTO = bookMapper.toDTO(book);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(bookDTO.id()).toUri();

		return ResponseEntity.created(location).body(bookDTO);
	}

	@PutMapping("/{id}")
	public BookDTO replaceBook(@PathVariable long id, @RequestBody BookDTO updatedBookDTO) throws SQLException {

		Book updatedBook = bookMapper.toDomain(updatedBookDTO);
		updatedBook = bookService.replaceBook(id, updatedBook);
		return bookMapper.toDTO(updatedBook);
	}

	@DeleteMapping("/{id}")
	public BookDTO deleteBook(@PathVariable long id) {

		return bookMapper.toDTO(bookService.deleteBook(id));
	}

	@PostMapping("/{id}/images/")
	public ResponseEntity<ImageDTO> createBookImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
			throws IOException {

		if (imageFile.isEmpty()) {
			throw new IllegalArgumentException("Image file cannot be empty");
		}

		Image image = imageService.createImage(imageFile.getInputStream());
		bookService.addImageToBook(id, image);

		URI location = fromCurrentContextPath()
				.path("/api/images/{imageId}/media")
				.buildAndExpand(image.getId())
				.toUri();

		return ResponseEntity.created(location).body(imageMapper.toDTO(image));
	}

	@DeleteMapping("/{bookId}/images/{imageId}")
	public ImageDTO deleteBookImage(@PathVariable long bookId, @PathVariable long imageId)
			throws IOException {

		Image image = imageService.getImage(imageId);
		bookService.removeImageFromBook(bookId);
		imageService.deleteImage(imageId);

		return imageMapper.toDTO(image);
	}
}

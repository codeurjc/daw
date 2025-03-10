package es.codeurjc.daw.library.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import es.codeurjc.daw.library.dto.BookDTO;
import es.codeurjc.daw.library.dto.BookMapper;
import es.codeurjc.daw.library.model.Book;
import es.codeurjc.daw.library.repository.BookRepository;
import es.codeurjc.daw.library.repository.ShopRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private BookMapper mapper;

	public Collection<BookDTO> getBooks() {

		return toDTOs(bookRepository.findAll());
	}

	public BookDTO getBook(long id) {

		return toDTO(bookRepository.findById(id).orElseThrow());
	}

	public BookDTO createBook(BookDTO bookDTO) {

		if(bookDTO.id() != null) {
			throw new IllegalArgumentException();
		}

		Book book = toDomain(bookDTO);

		bookRepository.save(book);

		//Load shop info from database to return shops in REST API
		book.getShops().replaceAll(shop -> shopRepository.findById(shop.getId()).orElseThrow());

		return toDTO(book);
	}

	public BookDTO replaceBook(long id, BookDTO updatedBookDTO) throws SQLException {

		Book oldBook = bookRepository.findById(id).orElseThrow();
		Book updatedBook = toDomain(updatedBookDTO);
		updatedBook.setId(id);

		if (oldBook.getImage() && updatedBook.getImage()) {

			// Transfer the imagen from the old book to the new one
			updatedBook.setImageFile(BlobProxy.generateProxy(oldBook.getImageFile().getBinaryStream(),
					oldBook.getImageFile().length()));
		}

		bookRepository.save(updatedBook);

		return toDTO(updatedBook);
	}

	public BookDTO createOrReplaceBook(Long id, BookDTO bookDTO) throws SQLException {
		
		BookDTO book;
		if(id == null) {
			book = createBook(bookDTO);
		} else {
			book = replaceBook(id, bookDTO);
		}
		return book;
	}

	public BookDTO deleteBook(long id) {

		Book book = bookRepository.findById(id).orElseThrow();

		//As books are related to shops, it is needed to load the book shops 
		//before deleting it to avoid LazyInitializationException
		BookDTO bookDTO = toDTO(book);

		bookRepository.deleteById(id);

		return bookDTO;
	}

	public Resource getBookImage(long id) throws SQLException {

		Book book = bookRepository.findById(id).orElseThrow();

		if (book.getImageFile() != null) {
			return new InputStreamResource(book.getImageFile().getBinaryStream());
		} else {
			throw new NoSuchElementException();
		}
	}

	public void createBookImage(long id, InputStream inputStream, long size) {

		Book book = bookRepository.findById(id).orElseThrow();

		book.setImage(true);
		book.setImageFile(BlobProxy.generateProxy(inputStream, size));

		bookRepository.save(book);
	}

	public void replaceBookImage(long id, InputStream inputStream, long size) {

		Book book = bookRepository.findById(id).orElseThrow();

		if (!book.getImage()) {
			throw new NoSuchElementException();
		}

		book.setImageFile(BlobProxy.generateProxy(inputStream, size));

		bookRepository.save(book);
	}

	public void deleteBookImage(long id) {

		Book book = bookRepository.findById(id).orElseThrow();

		if (!book.getImage()) {
			throw new NoSuchElementException();
		}

		book.setImageFile(null);
		book.setImage(false);

		bookRepository.save(book);
	}

	private BookDTO toDTO(Book book) {
		return mapper.toDTO(book);
	}

	private Book toDomain(BookDTO bookDTO) {
		return mapper.toDomain(bookDTO);
	}

	private Collection<BookDTO> toDTOs(Collection<Book> books) {
		return mapper.toDTOs(books);
	}

}

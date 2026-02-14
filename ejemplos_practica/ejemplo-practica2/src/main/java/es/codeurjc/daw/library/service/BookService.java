package es.codeurjc.daw.library.service;

import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.daw.library.model.Book;
import es.codeurjc.daw.library.model.Image;
import es.codeurjc.daw.library.repository.BookRepository;
import es.codeurjc.daw.library.repository.ShopRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ShopRepository shopRepository;

	public Collection<Book> getBooks() {

		return bookRepository.findAll();
	}

	public Book getBook(long id) {

		return bookRepository.findById(id).orElseThrow();
	}

	public Book createBook(Book book) {

		if (book.getId() != null) {
			throw new IllegalArgumentException();
		}

		bookRepository.save(book);

		// Load shop info from database to return shops in REST API
		book.getShops().replaceAll(shop -> shopRepository.findById(shop.getId()).orElseThrow());

		return book;
	}

	public Book replaceBook(long id, Book updatedBook) throws SQLException {

		Book oldBook = bookRepository.findById(id).orElseThrow();
		updatedBook.setId(id);

		if (oldBook.getImage() != null) {
			// Transfer the image from the old book to the new one
			updatedBook.setImage(oldBook.getImage());
		}

		if (updatedBook.getShops() == null) {
			updatedBook.setShops(oldBook.getShops());
		} else {
			// Load shop info from database to avoid LazyInitializationException
			updatedBook.getShops().replaceAll(shop -> shopRepository.findById(shop.getId()).orElseThrow());
		}

		bookRepository.save(updatedBook);

		return updatedBook;
	}

	public Book deleteBook(long id) {

		Book book = bookRepository.findById(id).orElseThrow();

		// As books are related to shops, it is needed to load the book shops
		// before deleting it to avoid LazyInitializationException
		// Force loading of shops
		book.getShops().size();

		bookRepository.deleteById(id);

		return book;
	}

	public Book addImageToBook(long id, Image image) {
		Book book = bookRepository.findById(id).orElseThrow();
		book.setImage(image);
		bookRepository.save(book);

		return book;
	}

	public Book removeImageFromBook(long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow();
		book.setImage(null);
		bookRepository.save(book);

		return book;
	}

}

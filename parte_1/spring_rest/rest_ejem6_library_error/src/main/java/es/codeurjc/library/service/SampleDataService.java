package es.codeurjc.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.library.domain.Author;
import es.codeurjc.library.domain.Book;
import es.codeurjc.library.repository.AuthorRepository;
import es.codeurjc.library.repository.BookRepository;
import jakarta.annotation.PostConstruct;

@Service
public class SampleDataService {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @PostConstruct
    public void initData() {

        Author autor1 = new Author("Antonio", "Español");
		Author autor2 = new Author("Gerard", "Frances");

		authorRepository.save(autor1);
		authorRepository.save(autor2);

        Book book1 = new Book("Bambi", 3);
		Book book2 = new Book("Batman", 4);
		Book book3 = new Book("Spiderman", 2);

        book1.getAuthors().add(autor1);
        book2.getAuthors().add(autor2);
        book3.getAuthors().add(autor2);

		bookRepository.save(book1);
		bookRepository.save(book2);
		bookRepository.save(book3);
		
    }
}

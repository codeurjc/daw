package es.codeurjc.daw.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
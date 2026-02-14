package es.codeurjc.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.library.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
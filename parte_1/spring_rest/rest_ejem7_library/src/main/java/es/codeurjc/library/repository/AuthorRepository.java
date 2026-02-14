package es.codeurjc.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.library.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
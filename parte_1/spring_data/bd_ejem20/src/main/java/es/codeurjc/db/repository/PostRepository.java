package es.codeurjc.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.db.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
}
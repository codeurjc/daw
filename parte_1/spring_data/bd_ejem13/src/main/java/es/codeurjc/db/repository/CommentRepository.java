package es.codeurjc.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.db.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}
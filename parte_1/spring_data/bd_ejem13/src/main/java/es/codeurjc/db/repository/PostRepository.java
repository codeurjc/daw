package es.codeurjc.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.db.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT DISTINCT p FROM Post p JOIN p.comments c WHERE c.author=?1")
	List<Post> findByCommentsAuthor(String author);	

}
package es.codeurjc.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUsername(String username);

	List<Post> findByTitle(String title);

}

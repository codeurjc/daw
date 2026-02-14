package es.codeurjc.board;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@PostConstruct
	public void init() {
		postRepository.save(new Post("Pepe", "Vendo moto", "Barata, barata"));
		postRepository.save(new Post("Juan", "Compro coche", "Pago bien"));
	}

	@GetMapping("/posts/")
	public Collection<Post> getPosts() {
		return postRepository.findAll();
	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<Post> getPost(@PathVariable long id) {

		Optional<Post> op = postRepository.findById(id);

		if (op.isPresent()) {
			Post post = op.get();
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/posts/")
	public ResponseEntity<Post> createPost(@RequestBody Post post) {

		postRepository.save(post);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

		return ResponseEntity.created(location).body(post);
	}

	@PutMapping("/posts/{id}")
	public ResponseEntity<Post> replacePost(@PathVariable long id, @RequestBody Post updatedPost) {

		if (postRepository.existsById(id)) {

			updatedPost.setId(id);
			postRepository.save(updatedPost);

			return ResponseEntity.ok(updatedPost);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/posts/{id}")
	public ResponseEntity<Post> deletePost(@PathVariable long id) {

		Optional<Post> op = postRepository.findById(id);

		if (op.isPresent()) {
			Post post = op.get();
			postRepository.deleteById(id);
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

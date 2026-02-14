package es.codeurjc.board;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@PostConstruct
	public void init() {
		postRepository.save(new Post("Pepe", "Vendo moto", "Barata, barata"));
		postRepository.save(new Post("Juan", "Compro coche", "Pago bien"));
	}

	@GetMapping("/")
	public Collection<PostDTO> getPosts() {
		
		return toDTOs(postRepository.findAll());
	}

	@GetMapping("/{id}")
	public PostDTO getPost(@PathVariable long id) {

		return toDTO(postRepository.findById(id).orElseThrow());
	}

	@PostMapping("/")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {

		Post post = toDomain(postDTO);

		postRepository.save(post);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

		return ResponseEntity.created(location).body(toDTO(post));
	}

	@PutMapping("/{id}")
	public PostDTO replacePost(@PathVariable long id, @RequestBody PostDTO updatedPostDTO) {

		if (postRepository.existsById(id)) {

			Post updatedPost = toDomain(updatedPostDTO);

			updatedPost.setId(id);
			postRepository.save(updatedPost);

			return toDTO(updatedPost);

		} else {
			throw new NoSuchElementException();
		}
	}

	@DeleteMapping("/{id}")
	public PostDTO deletePost(@PathVariable long id) {

		Post post = postRepository.findById(id).orElseThrow();

		postRepository.deleteById(id);

		return toDTO(post);
	}

	private PostDTO toDTO(Post post){
		return new PostDTO(post.getId(), post.getUsername(), post.getTitle(), post.getText());
	}

	private List<PostDTO> toDTOs(Collection<Post> posts) {
		return posts.stream().map(this::toDTO).toList();
	}

	private Post toDomain(PostDTO postDTO){
		return new Post(postDTO.id(), postDTO.username(), postDTO.title(), postDTO.text());
	}
}

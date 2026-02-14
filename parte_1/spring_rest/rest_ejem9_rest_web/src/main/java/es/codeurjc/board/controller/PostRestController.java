package es.codeurjc.board.controller;

import java.net.URI;
import java.util.Collection;
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

import es.codeurjc.board.domain.Post;
import es.codeurjc.board.dto.PostDTO;
import es.codeurjc.board.dto.PostMapper;
import es.codeurjc.board.service.PostService;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/posts")
public class PostRestController {

	@Autowired
	private PostService postService;

	@Autowired
	private PostMapper mapper;

	@GetMapping("/")
	public Collection<PostDTO> getPosts() {
	
		return mapper.toDTOs(postService.getPosts());
	}

	@GetMapping("/{id}")
	public PostDTO getPost(@PathVariable long id) {

		return mapper.toDTO(postService.getPost(id));
	}

	@PostMapping("/")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {

		Post post = mapper.toDomain(postDTO);
		post = postService.createPost(post);
		PostDTO responseDTO = mapper.toDTO(post);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.id()).toUri();

		return ResponseEntity.created(location).body(responseDTO);
	}

	@PutMapping("/{id}")
	public PostDTO replacePost(@PathVariable long id, @RequestBody PostDTO updatedPostDTO) {

		Post updatedPost = mapper.toDomain(updatedPostDTO);
		return mapper.toDTO(postService.replacePost(id, updatedPost));
	}

	@DeleteMapping("/{id}")
	public PostDTO deletePost(@PathVariable long id) {

		return mapper.toDTO(postService.deletePost(id));
	}
}

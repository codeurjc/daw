package es.codeurjc.board.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.board.domain.Image;
import es.codeurjc.board.dto.ImageDTO;
import es.codeurjc.board.dto.ImageMapper;
import es.codeurjc.board.dto.PostDTO;
import es.codeurjc.board.dto.PostMapper;
import es.codeurjc.board.service.ImageService;
import es.codeurjc.board.service.PostService;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private PostMapper postMapper;

	@Autowired
	private ImageService imageService;

	@Autowired
	private ImageMapper imageMapper;

	@GetMapping("/")
	public Collection<PostDTO> getPosts() {

		return postMapper.toDTOs(postService.getPosts());
	}

	@GetMapping("/{id}")
	public PostDTO getPost(@PathVariable long id) {

		return postMapper.toDTO(postService.getPost(id));
	}

	@PostMapping("/")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {

		postDTO = postMapper.toDTO(postService.createPost(postMapper.toDomain(postDTO)));

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(postDTO.id()).toUri();

		return ResponseEntity.created(location).body(postDTO);
	}

	@PutMapping("/{id}")
	public PostDTO replacePost(@PathVariable long id, @RequestBody PostDTO updatedPostDTO) {

		return postMapper.toDTO(postService.replacePost(id, postMapper.toDomain(updatedPostDTO)));
	}

	@DeleteMapping("/{id}")
	public PostDTO deletePost(@PathVariable long id) {

		return postMapper.toDTO(postService.deletePost(id));
	}

	@PostMapping("/{id}/images/")
	public ResponseEntity<ImageDTO> createPostImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
			throws IOException {

		if (imageFile.isEmpty()) {
			throw new IllegalArgumentException("Image file cannot be empty");
		}

		Image image = imageService.createImage(imageFile.getInputStream());
		postService.addImageToPost(id, image);

		URI location = fromCurrentContextPath()
				.path("/images/{imageId}/media")
				.buildAndExpand(image.getId())
				.toUri();

		return ResponseEntity.created(location).body(imageMapper.toDTO(image));
	}

	@DeleteMapping("/{postId}/images/{imageId}")
	public ImageDTO deletePostImage(@PathVariable long postId, @PathVariable long imageId)
			throws IOException {

		Image image = imageService.getImage(imageId);
		postService.removeImagePost(postId, image);
		imageService.deleteImage(imageId);

		return imageMapper.toDTO(image);
	}
}

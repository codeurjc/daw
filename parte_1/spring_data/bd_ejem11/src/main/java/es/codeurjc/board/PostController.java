package es.codeurjc.board;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.PostConstruct;

@Controller
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@PostConstruct
	public void init() {
		postRepository.save(new Post("Pepe", "Vendo moto", "Barata, barata"));
		postRepository.save(new Post("Juan", "Compro coche", "Pago bien"));
	}

	@GetMapping("/")
	public String showPosts(Model model, @RequestParam(required = false) String username) {
		if (username != null) {
			model.addAttribute("posts", postRepository.findByUsername(username));
		} else {
			model.addAttribute("posts", postRepository.findAll());
		}
		return "index";
	}

	@GetMapping("/post/{id}")
	public String showPost(Model model, @PathVariable long id) {

		Optional<Post> op = postRepository.findById(id);

		if (op.isPresent()) {
			Post post = op.get();
			model.addAttribute("post", post);
			return "show_post";	
		} else {
			return "post_not_found";
		}
	}

	@PostMapping("/post/new")
	public String newPost(Model model, Post post) {

		postRepository.save(post);

		return "saved_post";
	}

	@GetMapping("/editpost/{id}")
	public String editBook(Model model, @PathVariable long id) {

		Optional<Post> op = postRepository.findById(id);
		if (op.isPresent()) {
			Post post = op.get();
			model.addAttribute("post", post);
			return "edit_post_page";
		} else {
			return "post_not_found";
		}
	}

	@PostMapping("/editpost")
	public String editBookProcess(Model model, Post editedPost) {

		Optional<Post> op = postRepository.findById(editedPost.getId());
		if (op.isPresent()) {
			postRepository.save(editedPost);
			model.addAttribute("post", editedPost);
			return "edited_post";
		} else {
			return "post_not_found";
		}
	}

	@PostMapping("/post/{id}/delete")
	public String deletePost(Model model, @PathVariable long id) {

		Optional<Post> post = postRepository.findById(id);

		if (post.isPresent()) {
			postRepository.deleteById(id);
			return "deleted_post";
		} else {
			return "post_not_found";
		}
	}

}

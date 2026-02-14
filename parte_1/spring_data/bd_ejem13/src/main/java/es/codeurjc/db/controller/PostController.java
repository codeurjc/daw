package es.codeurjc.db.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.db.model.Comment;
import es.codeurjc.db.model.Post;
import es.codeurjc.db.repository.CommentRepository;
import es.codeurjc.db.repository.PostRepository;
import jakarta.annotation.PostConstruct;

@Controller
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@PostConstruct
	public void init() {

		Post post = new Post("Spring Post", "Spring is cool");
		post.getComments().add(new Comment("Pepe", "Cool!"));
		post.getComments().add(new Comment("Juan", "Very cool"));

		Post post2 = new Post("Java Post", "Java is cool");
		post2.getComments().add(new Comment("Pepe", "Cool!"));
		post2.getComments().add(new Comment("Michel", "Very cool"));

		postRepository.save(post);
		postRepository.save(post2);
	}

	@GetMapping("/")
	public String getPosts(Model model, @RequestParam(required = false) String author) throws Exception {
		if (author != null) {
			model.addAttribute("posts", postRepository.findByCommentsAuthor(author));
		} else {
			model.addAttribute("posts", postRepository.findAll());
		}
		return "index";
	}

	@PostMapping("/posts/new")
	public String newPost(Model model, Post post) {
		postRepository.save(post);
		return "saved_post";
	}

	@GetMapping("/posts/{id}")
	public String getPost(Model model, @PathVariable long id) {
		Optional<Post> post = postRepository.findById(id);
		if (post.isPresent()) {
			model.addAttribute("post", post.get());
			return "show_post";
		} else {
			return "post_not_found";
		}
	}

	// Deleting a post delete its associated comments
	@PostMapping("/posts/{id}/delete")
	public String deletePost(@PathVariable long id) {
		Optional<Post> post = postRepository.findById(id);
		if (post.isPresent()) {
			postRepository.deleteById(id);
			return "redirect:/";
		} else {
			return "post_not_found";
		}
	}

	@PostMapping("/posts/{postId}/comments/new")
	public String newComment(@PathVariable long postId, Comment comment) {
		Post post = postRepository.findById(postId).orElseThrow();
		post.getComments().add(comment);
		commentRepository.save(comment);
		return "redirect:/posts/" + postId;
	}

	@PostMapping("/posts/{postId}/comments/{commentId}/delete")
	public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
		Post post = postRepository.findById(postId).orElseThrow();
		Comment comment = commentRepository.findById(commentId).orElseThrow();
		post.getComments().remove(comment);
		commentRepository.deleteById(commentId);
		return "redirect:/posts/" + postId;
	}

}

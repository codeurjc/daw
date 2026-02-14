package es.codeurjc.db.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.db.model.Comment;
import es.codeurjc.db.model.Image;
import es.codeurjc.db.model.Post;
import es.codeurjc.db.service.CommentService;
import es.codeurjc.db.service.ImageService;
import es.codeurjc.db.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private ImageService imageService;

	@GetMapping("/")
	public String getPosts(Model model) {
		model.addAttribute("posts", postService.findAll());
		return "index";
	}

	@PostMapping("/posts/new")
	public String newPost(Model model, Post post, MultipartFile imageOneFile, MultipartFile imageTwoFile)
			throws Exception {

		postService.save(post);

		if (!imageOneFile.isEmpty()) {
			Image imageOne = imageService.createImage(imageOneFile);
			postService.addImageToPost(post.getId(), imageOne);
		}

		if (!imageTwoFile.isEmpty()) {
			Image imageTwo = imageService.createImage(imageTwoFile);
			postService.addImageToPost(post.getId(), imageTwo);
		}

		return "saved_post";
	}

	@GetMapping("/posts/{id}")
	public String getPost(Model model, @PathVariable long id) {
		Optional<Post> post = postService.findById(id);
		if (post.isPresent()) {
			model.addAttribute("post", post.get());
			model.addAttribute("hasImages", !post.get().getImages().isEmpty());
			return "show_post";
		} else {
			return "post_not_found";
		}
	}

	// Deleting a post delete its associated comments
	@PostMapping("/posts/{id}/delete")
	public String deletePost(@PathVariable long id) {
		Optional<Post> post = postService.findById(id);
		if (post.isPresent()) {
			postService.deleteById(id);
			return "redirect:/";
		} else {
			return "post_not_found";
		}
	}

	@PostMapping("/posts/{postId}/comments/new")
	public String newComment(@PathVariable long postId, Comment comment) {
		Post post = postService.findById(postId).get();
		post.getComments().add(comment);
		commentService.save(comment);
		return "redirect:/posts/" + postId;
	}

	@PostMapping("/posts/{postId}/comments/{commentId}/delete")
	public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
		Comment comment = commentService.findById(commentId).get();
		commentService.delete(comment, postId);
		return "redirect:/posts/" + postId;
	}

}

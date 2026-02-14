package es.codeurjc.db.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.db.model.Comment;
import es.codeurjc.db.model.Post;
import es.codeurjc.db.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentsRepository;

	@Autowired
	private PostService postService;

	public Optional<Comment> findById(long id) {

		return commentsRepository.findById(id);
	}

	public void save(Comment comment) {		
		this.commentsRepository.save(comment);
	}

	public void delete(Comment comment, Long postId) {
		Post post = postService.findById(postId).orElseThrow();
		post.getComments().remove(comment);
		this.commentsRepository.delete(comment);		
	}
}

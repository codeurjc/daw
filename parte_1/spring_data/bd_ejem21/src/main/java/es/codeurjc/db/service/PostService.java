package es.codeurjc.db.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.db.model.Image;
import es.codeurjc.db.model.Post;
import es.codeurjc.db.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public void save(Post post) {
		postRepository.save(post);
	}

	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public Optional<Post> findById(long id) {
		return postRepository.findById(id);
	}

	public void deleteById(long id) {

		postRepository.deleteById(id);
	}

	public Post addImageToPost(long id, Image image) {
		Post post = postRepository.findById(id).orElseThrow();
		post.getImages().add(image);
		postRepository.save(post);

		return post;
	}

}

package es.codeurjc.board.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.board.domain.Image;
import es.codeurjc.board.domain.Post;
import es.codeurjc.board.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Collection<Post> getPosts() {

		return postRepository.findAll();
	}

	public Post getPost(long id) {

		return postRepository.findById(id).orElseThrow();
	}

	public Post createPost(Post post) {

		postRepository.save(post);

		return post;
	}

	public Post replacePost(long id, Post updatedPost) {
		Post post = postRepository.findById(id).orElseThrow();

		updatedPost.setId(id);
		updatedPost.setImages(post.getImages());

		postRepository.save(updatedPost);

		return updatedPost;
	}

	public Post deletePost(long id) {

		Post post = postRepository.findById(id).orElseThrow();

		postRepository.deleteById(id);

		return post;
	}

	public Post addImageToPost(long id, Image image) {
		Post post = postRepository.findById(id).orElseThrow();
		post.getImages().add(image);
		postRepository.save(post);

		return post;
	}

	public Post removeImagePost(long postId, Image image) {
		Post post = postRepository.findById(postId).orElseThrow();
		post.getImages().remove(image);
		postRepository.save(post);

		return post;
	}
}

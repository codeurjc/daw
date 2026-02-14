package es.codeurjc.board.service;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

		if (postRepository.existsById(id)) {

			updatedPost.setId(id);

			postRepository.save(updatedPost);

			return updatedPost;

		} else {
			throw new NoSuchElementException();
		}
	}

	public Post deletePost(long id) {

		Post post = postRepository.findById(id).orElseThrow();

		postRepository.deleteById(id);

		return post;
	}

}
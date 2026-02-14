package es.codeurjc.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.db.model.Comment;
import es.codeurjc.db.model.Post;
import jakarta.annotation.PostConstruct;

@Service
public class SampleDataService {

	@Autowired
	private PostService postRepository; 
	
	@PostConstruct
	public void init() {
		Post post = new Post("Spring Post", "Spring is cool");
		post.getComments().add(new Comment("Pepe", "Cool!"));
		post.getComments().add(new Comment("Juan", "Very cool"));

		postRepository.save(post);
	}
}

package es.codeurjc.db.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.db.model.Post;
import es.codeurjc.db.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	public void save(Post post) {
		postRepository.save(post);
	}

	public void save(Post post, MultipartFile imageFile) throws IOException{
		if(!imageFile.isEmpty()) {
			try {
				post.setImageFile(new SerialBlob(imageFile.getBytes()));
			} catch (Exception e) {
				throw new IOException("Failed to create image blob", e);
			}
		}
		this.save(post);
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

}

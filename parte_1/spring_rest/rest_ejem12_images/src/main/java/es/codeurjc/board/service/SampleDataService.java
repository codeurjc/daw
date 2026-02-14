package es.codeurjc.board.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import es.codeurjc.board.domain.Image;
import es.codeurjc.board.domain.Post;
import es.codeurjc.board.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class SampleDataService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImageService imageService;

    @PostConstruct
    public void init() throws Exception {
        loadSampleData();
    }

    @Transactional
    public void loadSampleData() throws IOException {
        Post post1 = new Post("Pepe", "Vendo moto", "Barata, barata");
        Post post2 = new Post("Juan", "Compro coche", "Pago bien");

        postRepository.save(post1);
        postRepository.save(post2);

        setPostImage(post1, "/sampledata_images/moto.jpg");
    }

    public void setPostImage(Post post, String classpathResource) throws IOException {
        Resource image = new ClassPathResource(classpathResource);

        Image createdImage = imageService.createImage(image.getInputStream());
        post.getImages().add(createdImage);
        postRepository.save(post);
    }
}

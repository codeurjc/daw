package es.codeurjc.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.board.domain.Post;
import es.codeurjc.board.repository.PostRepository;
import jakarta.annotation.PostConstruct;

@Service
public class SampleDataService {

    @Autowired
    private PostRepository postRepository;

    @PostConstruct
    public void init() {
        postRepository.save(new Post("Pepe", "Vendo moto", "Barata, barata"));
        postRepository.save(new Post("Juan", "Compro coche", "Pago bien"));
    }
}

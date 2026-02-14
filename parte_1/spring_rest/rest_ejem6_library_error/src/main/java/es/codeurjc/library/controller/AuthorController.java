package es.codeurjc.library.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.library.dto.AuthorDTO;
import es.codeurjc.library.dto.AuthorMapper;
import es.codeurjc.library.repository.AuthorRepository;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper mapper;

    @GetMapping("/")
    public Collection<AuthorDTO> getAllAuthors() {
        return mapper.toDTOs(authorRepository.findAll());
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable Long id) {
        return mapper.toDTO(authorRepository.findById(id).orElseThrow());
    }
}
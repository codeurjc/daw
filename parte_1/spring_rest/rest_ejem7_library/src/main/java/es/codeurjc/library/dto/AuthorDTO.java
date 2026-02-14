package es.codeurjc.library.dto;

import java.util.List;

public record AuthorDTO(Long id, String name, String nationality, List<BookBasicDTO> books) {
}
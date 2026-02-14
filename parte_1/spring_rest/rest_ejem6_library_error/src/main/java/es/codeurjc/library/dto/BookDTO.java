package es.codeurjc.library.dto;

import java.util.List;

public record BookDTO(Long id, String title, int price, List<AuthorDTO> authors) {
}
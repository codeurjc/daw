package es.codeurjc.board.dto;

import java.util.List;

public record PostDTO(
        Long id,
        String username,
        String title,
        String text,
        List<ImageDTO> images) {
}
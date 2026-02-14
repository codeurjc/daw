package es.codeurjc.board.dto;

public record PostDTO(
        Long id,
        String username,
        String title,
        String text) {
}
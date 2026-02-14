package es.codeurjc.board;

public record PostDTO(
        Long id,
        String username,
        String title,
        String text) {
}
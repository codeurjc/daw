package es.codeurjc.daw.library.dto;

import java.util.List;

public record BookDTO(
        Long id,
        String title,
        String description,
        ImageDTO image,
        List<ShopBasicDTO> shops) {
}
package es.codeurjc.daw.library.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public record NewBookRequestDTO (
        String title,
        String description,
        MultipartFile imageField,
        List<Long> shops){
}
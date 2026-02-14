package es.codeurjc.daw.library.dto;

import org.mapstruct.Mapper;

import es.codeurjc.daw.library.model.Image;

@Mapper(componentModel = "spring")
public interface ImageMapper {

	ImageDTO toDTO(Image image);
}

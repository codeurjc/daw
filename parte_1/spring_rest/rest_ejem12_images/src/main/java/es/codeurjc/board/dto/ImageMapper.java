package es.codeurjc.board.dto;

import org.mapstruct.Mapper;

import es.codeurjc.board.domain.Image;

@Mapper(componentModel = "spring")
public interface ImageMapper {

	ImageDTO toDTO(Image image);
}

package es.codeurjc.library.dto;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.codeurjc.library.domain.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO toDTO(Author author);

    List<AuthorBasicDTO> toDTOs(Collection<Author> authors);

    @Mapping(target = "books", ignore = true)
    Author toDomain(AuthorBasicDTO authorDTO);
}
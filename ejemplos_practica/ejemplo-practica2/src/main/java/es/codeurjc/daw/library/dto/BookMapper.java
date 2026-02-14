package es.codeurjc.daw.library.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.codeurjc.daw.library.model.Book;
import es.codeurjc.daw.library.model.Shop;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toDTO(Book book);

    List<BookDTO> toDTOs(Collection<Book> books);

    @Mapping(target = "image", ignore = true)
    Book toDomain(BookDTO bookDTO);

    @Mapping(target = "books", ignore = true)
    Shop toDomain(ShopBasicDTO shopDTO);
}

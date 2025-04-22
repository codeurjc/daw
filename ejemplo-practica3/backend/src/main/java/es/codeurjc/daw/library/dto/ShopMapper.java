package es.codeurjc.daw.library.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.codeurjc.daw.library.model.Shop;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    ShopBasicDTO toDTO(Shop shop);

    List<ShopBasicDTO> toDTOs(Collection<Shop> shops);

    @Mapping(target = "books", ignore = true)
    Shop toDomain(ShopBasicDTO shopDTO);
}

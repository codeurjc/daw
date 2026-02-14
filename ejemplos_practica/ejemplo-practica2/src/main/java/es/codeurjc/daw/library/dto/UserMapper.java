package es.codeurjc.daw.library.dto;

import org.mapstruct.Mapper;

import es.codeurjc.daw.library.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
    
}
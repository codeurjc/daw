package es.codeurjc.board.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.codeurjc.board.domain.Post;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    
    PostDTO toDTO(Post post);

    List<PostDTO> toDTOs(Collection<Post> posts);

    @Mapping(target = "images", ignore = true)
    Post toDomain(PostDTO postDTO);
}

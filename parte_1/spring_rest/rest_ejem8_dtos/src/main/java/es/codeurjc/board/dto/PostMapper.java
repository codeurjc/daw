package es.codeurjc.board.dto;

import org.mapstruct.Mapper;

import es.codeurjc.board.domain.Post;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDTO toDTO(Post post);

    List<PostDTO> toDTOs(Collection<Post> posts);

    Post toDomain(PostDTO postDTO);
}

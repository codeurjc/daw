package es.codeurjc.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.board.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}

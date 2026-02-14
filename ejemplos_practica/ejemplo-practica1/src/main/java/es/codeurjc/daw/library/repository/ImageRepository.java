package es.codeurjc.daw.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.library.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}

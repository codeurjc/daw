package es.codeurjc.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.db.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}

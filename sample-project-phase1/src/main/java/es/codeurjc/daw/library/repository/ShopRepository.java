package es.codeurjc.daw.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.library.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

}
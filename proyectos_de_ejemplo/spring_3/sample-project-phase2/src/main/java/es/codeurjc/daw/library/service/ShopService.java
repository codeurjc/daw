package es.codeurjc.daw.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.daw.library.model.Shop;
import es.codeurjc.daw.library.repository.ShopRepository;

@Service
public class ShopService {

	@Autowired
	private ShopRepository repository;

	public Optional<Shop> findById(long id) {
		return repository.findById(id);
	}

	public List<Shop> findById(List<Long> ids){
		return repository.findAllById(ids);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Shop> findAll() {
		return repository.findAll();
	}

	public void save(Shop Shop) {
		repository.save(Shop);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

	
}

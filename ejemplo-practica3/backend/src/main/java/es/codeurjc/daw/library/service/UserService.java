package es.codeurjc.daw.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.daw.library.dto.UserDTO;
import es.codeurjc.daw.library.dto.UserMapper;
import es.codeurjc.daw.library.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper mapper;

	public UserDTO getUser(String name) {

		return mapper.toDTO(userRepository.findByName(name).orElseThrow());
	}

}

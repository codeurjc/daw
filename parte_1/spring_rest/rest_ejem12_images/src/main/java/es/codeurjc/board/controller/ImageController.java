package es.codeurjc.board.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.board.dto.ImageDTO;
import es.codeurjc.board.dto.ImageMapper;
import es.codeurjc.board.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

	@Autowired
	private ImageService imageService;

	@Autowired
	private ImageMapper mapper;

	@GetMapping("/{id}")
	public ImageDTO getImage(@PathVariable long id) {
		return mapper.toDTO(imageService.getImage(id));
	}

	@GetMapping("/{id}/media")
	public ResponseEntity<Object> getImageFile(@PathVariable long id)
			throws SQLException, IOException {

		Resource imageFile = imageService.getImageFile(id);

		MediaType mediaType = MediaTypeFactory
				.getMediaType(imageFile)
				.orElse(MediaType.IMAGE_JPEG);

		return ResponseEntity
				.ok()
				.contentType(mediaType)
				.body(imageFile);
	}

	@PutMapping("/{id}/media")
	public ResponseEntity<Object> replaceImageFile(@PathVariable long id,
			@RequestParam MultipartFile imageFile) throws IOException {

		imageService.replaceImageFile(id, imageFile.getInputStream());
		return ResponseEntity.noContent().build();
	}
}

package es.codeurjc.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

	private static final Path IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");

	@PostMapping("/upload_image")
	public String uploadImage(@RequestParam String imageName, @RequestParam MultipartFile image, Model model)
			throws IOException {

		Files.createDirectories(IMAGES_FOLDER);
		
		Path imagePath = IMAGES_FOLDER.resolve(imageName);
		
		image.transferTo(imagePath);

		model.addAttribute("imageName", imageName);

		return "uploaded_image";
	}

	@GetMapping("/image")
	public String viewImage(Model model, @RequestParam String imageName) {

		model.addAttribute("imageName", imageName);

		return "view_image";
	}

	@GetMapping("/download_image")	
	public ResponseEntity<Object> downloadImage(Model model, @RequestParam String imageName) throws MalformedURLException {

		Path imagePath = IMAGES_FOLDER.resolve(imageName);
		
		Resource image = new UrlResource(imagePath.toUri());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(image);		
	}
}

package es.codeurjc.books;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;

@RestController
public class BooksController {

	@GetMapping("/booktitles")
	public List<String> getBookTitles(@RequestParam String title) {

		RestClient restClient = RestClient.create();

        ObjectNode data = restClient.get()
            .uri("https://www.googleapis.com/books/v1/volumes?q=intitle:" + title)
            .retrieve()
            .body(ObjectNode.class);

        List<String> bookTitles = new ArrayList<>();

		ArrayNode items = (ArrayNode) data.get("items");
		for (int i = 0; i < items.size(); i++) {
			JsonNode item = items.get(i);
			JsonNode titleNode = item.get("volumeInfo").get("title");
			String bookTitle = titleNode != null ? titleNode.asString("") : "";
			bookTitles.add(bookTitle);
		}

		return bookTitles;
	}
}

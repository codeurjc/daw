package es.codeurjc.daw.library.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String address;

	@ManyToMany(mappedBy="shops")
 	private List<Book> books;


	public Shop() {}

	public Shop(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public List<Book> getBooks() {
		return books;
	}


	public void setBooks(List<Book> books) {
		this.books = books;
	}
}

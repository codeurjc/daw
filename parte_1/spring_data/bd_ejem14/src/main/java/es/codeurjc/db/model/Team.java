package es.codeurjc.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;	
	
	public Team() {
		super();
	}

	public Team(String data) {
		super();
		this.name = data;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", data=" + name + "]";
	}
}

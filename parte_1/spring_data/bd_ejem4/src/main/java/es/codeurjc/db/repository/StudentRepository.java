package es.codeurjc.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.db.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
}
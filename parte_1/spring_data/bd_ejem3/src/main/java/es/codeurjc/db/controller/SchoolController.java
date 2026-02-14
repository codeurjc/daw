package es.codeurjc.db.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.db.model.Project;
import es.codeurjc.db.model.Student;
import es.codeurjc.db.repository.ProjectRepository;
import es.codeurjc.db.repository.StudentRepository;
import jakarta.annotation.PostConstruct;

@Controller
public class SchoolController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@PostConstruct
	public void init() {

		Project p1 = new Project("TFG1", 8);
		projectRepository.save(p1);
		
		Student s1 = new Student("Pepe", 2010);
		s1.setProject(p1);
		
		Student s2 = new Student("Juan", 2011);
		
		studentRepository.save(s1);
		studentRepository.save(s2);		
	}

	@GetMapping("/")
	public String getSchool(Model model) {
		model.addAttribute("students", studentRepository.findAll());
		model.addAttribute("projects", projectRepository.findAll());
		return "index";
	}

	@GetMapping("/students/{id}")
	public String getStudent(@PathVariable Long id, Model model) {
		Optional<Student> student = studentRepository.findById(id);
		if(student.isPresent()) {
			model.addAttribute("student", student.get());
			return "show_student";
		} else {
			return "student_not_found";
		}
	}

	@PostMapping("/students/new")
	public String createStudent(Student student) {
		studentRepository.save(student);
		return "saved_student";
	}

	@PostMapping("/students/{studentId}/project")
	public String createProject(Model model, @PathVariable Long studentId, Project project) {

		// Save the project first
		projectRepository.save(project);

		// Associate the project with the student
		Student student = studentRepository.findById(studentId).get();
		student.setProject(project);

		// Save the student
		studentRepository.save(student);

		return "redirect:/students/"+studentId;
	}
	
	// Deleting a student doesn't delete his/her associated project
	@PostMapping("/students/{id}/delete")
	public String deleteStudent(@PathVariable Long id, Model model) {
		studentRepository.deleteById(id);
		return "deleted_student";
	}
	
	// A project only can be deleted if it has no associated student.
	@PostMapping("/students/{studentId}/project/delete")
	public String deleteProject(Model model, @PathVariable Long studentId) {

		// Get the student and the project
		Student student = studentRepository.findById(studentId).get();
		Project project = student.getProject();

		// Disassociate the project from the student
		student.setProject(null);

		// Save the student and delete the project
		studentRepository.save(student);
		projectRepository.delete(project);

		return "redirect:/students/"+studentId;
	}
	
}

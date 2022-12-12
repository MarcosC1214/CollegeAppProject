package controller;


import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;

import model.Student;
import model.University;
import repositories.StudentRepository;
import security.LoggedStudent;

public class StudentController {

	private StudentRepository studentRepository;
	
	public StudentController() {
		this.studentRepository = new StudentRepository();
	}
	
	
	public Student loginStudent(String username, String password) {
		;
		Student result = null;
		try {
			List<Student> students = this.studentRepository.getAll();
			if(students == null) {
				return null;
			}
			
			for(Student st: students) {
				if(st.getUsername().equals(username) && st.getPassword().equals(password)) {
					result = st;
					break;
				}
			}
			LoggedStudent.setCurrentStudent(result);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public Student registerStudent(String name, String username, String password) throws JsonProcessingException, Exception {
		Student student = new Student();
		UUID uuid = UUID.randomUUID();
		//todo load students and check for no duplicates
		student.setId(uuid.toString());
		student.setName(name);
		student.setUsername(username);
		student.setPassword(password);
		
		
		return this.studentRepository.create(student);
		
	}


	public Student addUniversitiesToCurrentUser(List<University> selectedUniversities) throws JsonProcessingException, Exception {
		// TODO Auto-generated method stub
		Student st = this.studentRepository.getById(LoggedStudent.getLoggedStudent().getId());
		if(st != null) {
			//todo filterout duplications 
			st.getUniversities().addAll(selectedUniversities);
			Student updated =  this.studentRepository.update(st, st.getId());
			LoggedStudent.setCurrentStudent(updated);
			return updated;
		}
		return null;
	}


	public boolean  removeAccount() {
		// TODO Auto-generated method stub
		
		String id = LoggedStudent.getLoggedStudent().getId();
		boolean success = this.studentRepository.deleteStudent(id);
		LoggedStudent.setCurrentStudent(null);
		return success;
		
	}

	private int findByID(List<University> universities, long id) {
		int index = -1;
		for(int i = 0; i< universities.size(); i++) {
			University u = universities.get(i);
			if(u.getId() == id) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	public Student removeUniversities(List<University> selectedUniversities) throws JsonProcessingException, Exception {
		// TODO Auto-generated method stub
		Student st = this.studentRepository.getById(LoggedStudent.getLoggedStudent().getId());
		if(st != null) {
			for (University university : selectedUniversities) {
				int index = findByID(st.getUniversities(), university.getId());
				if(index != -1) {
					st.getUniversities().remove(index);
				}
			}
			Student updated =  this.studentRepository.update(st, st.getId());
			LoggedStudent.setCurrentStudent(updated);
			return updated;
		}
		return null;
	}
	
	
	
}

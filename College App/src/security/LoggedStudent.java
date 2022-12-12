package security;

import model.Student;

public class LoggedStudent {
	private static Student currentStudent;
	
	public static void setCurrentStudent(Student student) {
		LoggedStudent.currentStudent = student;
	}
	
	public static Student getLoggedStudent() {
		return currentStudent;
	}
	
	public static boolean isLogged() {
		return currentStudent != null;	
	}
	
}

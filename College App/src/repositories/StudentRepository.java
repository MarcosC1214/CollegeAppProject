package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import model.Student;
import kong.unirest.GenericType;


public class StudentRepository  {

	public static final String studentApi = "http://localhost:3000/students";
	HashMap<String, String> headers = new HashMap<>();

	public StudentRepository() {
		Unirest.config().setDefaultHeader("Content-Type", "application/json");
		headers.put("accept", "application/json");
	}
	
	
	public List<Student> getAll() throws Exception, JsonProcessingException {
		List<Student> students = new ArrayList<>();
		HttpResponse<String> response = Unirest.get(studentApi).asString();
		if(response.isSuccess()) {
			ObjectMapper objectMapper = new ObjectMapper();
			students = objectMapper.readValue(response.getBody(), new TypeReference<List<Student>>(){});

		}
		return students;
		
	}
	
	private Student mapStudentResponse(HttpResponse<String> response) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Student student = objectMapper.readValue(response.getBody(), Student.class);
		return student;
	}
	
	public Student create(Student st) throws Exception, JsonProcessingException {
		 ObjectMapper mapper = new ObjectMapper();
		 String jsonInString = mapper.writeValueAsString(st);
		 HttpResponse<String> response  =  Unirest.post(studentApi)
				 .body(jsonInString).asString();
		 return this.mapStudentResponse(response);
	}
	
	public Student getById(String id) throws Exception, JsonProcessingException {
		 HttpResponse<String> response  = Unirest.get(studentApi + "/{id}")
				 .routeParam("id", id)
				 .asString();
		 return this.mapStudentResponse(response);
	}
	public Student update(Student st, String id) throws Exception, JsonProcessingException {
		 ObjectMapper mapper = new ObjectMapper();
		 String jsonInString = mapper.writeValueAsString(st);
		 HttpResponse<String> response  = Unirest.put(studentApi + "/{id}")
				 .routeParam("id", id)
				 .body(jsonInString)
				 .asString();
		 return this.mapStudentResponse(response);
	}


	public boolean deleteStudent(String id) {
		// TODO Auto-generated method stub
		HttpResponse<String> response  = Unirest.delete(studentApi + "/{id}")
				 .routeParam("id", id)
				 .asEmpty();
		return response.isSuccess();
	}

}

package controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import model.University;

public class ApiController {

	
	public List<University> getUniversitiesByName(String name) {
		List<University> result = new ArrayList<>();

		try {
			
			HttpResponse<String>  response = Unirest.get("https://api.data.gov/ed/collegescorecard/v1/schools").queryString("api_key", "htWk1Quhbm9JyqbZXFjhShB8HDwWcT4gBrogfEON")
	        		.queryString("fields", "id,school.name,school.city,school.state,latest.cost.avg_net_price.overall,latest.cost.booksupply,latest.cost.roomboard.oncampus,latest.cost.tuition.in_state,latest.cost.tuition.out_of_state")
	        		.queryString("per_page", "1000")
	        		.queryString("sort", "school.name:desc")
	        		.queryString("school.search", name).asString();
			
			if(response.isSuccess()) {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = (ObjectNode) mapper.readTree(response.getBody());
				ArrayNode list =  (ArrayNode) root.get("results");
				for(JsonNode node : list) {
					University universityApi = new University();
					universityApi.setId(node.get("id") != null ? node.get("id").asLong() : null);
					universityApi.setName(node.get("school.name") != null ? node.get("school.name").asText() : null);
					universityApi.setCity(node.get("school.city") != null ? node.get("school.city").asText() : null);
					universityApi.setState(node.get("school.state") != null ? node.get("school.state").asText() : null);
					universityApi.setOverallAvg(node.get("latest.cost.avg_net_price.overall") != null ? node.get("latest.cost.avg_net_price.overall").asInt() : null);
					universityApi.setBooksupply(node.get("latest.cost.booksupply") != null ? node.get("latest.cost.booksupply").asInt() : null);
					universityApi.setRoomBoard(node.get("latest.cost.roomboard.oncampus") != null ? node.get("latest.cost.roomboard.oncampus").asInt() : null);
					universityApi.setTuitionInState(node.get("latest.cost.tuition.in_state") != null ? node.get("latest.cost.tuition.in_state").asInt() : null);
					universityApi.setTuitionOutState(node.get("latest.cost.tuition.out_of_state") != null ? node.get("latest.cost.tuition.out_of_state").asInt() : null);
					result.add(universityApi);
				}
				response.getStatus();
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
		
	
		
	}
}

package model;

public class University {

	String name;
	long id;
	String state;
	Integer cost;
	String city;
	Integer booksupply;
	Integer overallAvg;
	Integer roomBoard; 
	Integer tuitionInState;
	Integer tuitionOutState;
	
	
	public University() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getOverallAvg() {
		return overallAvg;
	}
	public void setOverallAvg(Integer overallAvg) {
		this.overallAvg = overallAvg;
	}
	
	
	public Integer getBooksupply() {
		return booksupply;
	}

	public void setBooksupply(Integer booksupply) {
		this.booksupply = booksupply;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getRoomBoard() {
		return roomBoard;
	}

	public void setRoomBoard(Integer roomBoard) {
		this.roomBoard = roomBoard;
	}

	public Integer getTuitionInState() {
		return tuitionInState;
	}

	public void setTuitionInState(Integer tuitionInState) {
		this.tuitionInState = tuitionInState;
	}

	public Integer getTuitionOutState() {
		return tuitionOutState;
	}

	public void setTuitionOutState(Integer tuitionOutState) {
		this.tuitionOutState = tuitionOutState;
	}

	
	@Override
	public String toString() {
		return  name + "- " + state + ", " + city + "- " + "COA: $" + overallAvg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		University other = (University) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	
}

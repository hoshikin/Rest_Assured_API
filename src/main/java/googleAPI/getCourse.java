package googleAPI;

public class getCourse {
	
	private String services;
	private String expertise;
	//return type of below courses variable is coming from Courses class, hence the its changed to Courses from String
	private Courses Courses;
	private String instructor;
	private String linkedIn;
	
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public googleAPI.Courses getCourses() {
		return Courses;
	}
	public void setCourses(googleAPI.Courses courses) {
		Courses = courses;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	

}

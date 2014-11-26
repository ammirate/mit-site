package beans;

public class Curriculum {

	int id;
	String title;
	String degreeId;
	
	
	
	public Curriculum(String title, String degreeId) {
		super();
		this.title = title;
		this.degreeId = degreeId;
	}
	
	
	public Curriculum(int id, String title, String degreeId) {
		super();
		this.id = id;
		this.title = title;
		this.degreeId = degreeId;
	}


	public Curriculum() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDegreeId() {
		return degreeId;
	}
	public void setDegreeId(String degreeId) {
		this.degreeId = degreeId;
	}

	

	@Override
	public String toString() {
		return "id=" + id + ", title=\"" + title + "\", degreeId=\""
				+ degreeId + "\"";
	}


	public String toStringQueryInsert() {
		// TODO Auto-generated method stub
		return "\""+title+"\",\""+degreeId+"\"";
	}
	
	
}

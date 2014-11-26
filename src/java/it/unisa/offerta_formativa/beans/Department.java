package it.unisa.offerta_formativa.beans;

public class Department {

	private String title;
	private String urlMoodle;
	private String token;
	private int id;
	
	public Department(String title, String urlMoodle, String token){
		if(title.equalsIgnoreCase("")) throw new IllegalArgumentException("Title cannot be null");
		this.title=title;
		this.urlMoodle=urlMoodle;
		this.token=token;
	}
	
	public Department(int id,String title, String urlMoodle, String token){
		this(title,urlMoodle,token);
		if(id<=0) throw new IllegalArgumentException("id cannot be less than one");
		this.id=id;
	}
	
	public Department() {
		// TODO Auto-generated constructor stub
		this.id=-1;
		this.title="";
		this.urlMoodle="";
		this.token="";
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if(title.equalsIgnoreCase("")) throw new IllegalArgumentException("Title cannot be null");
		this.title = title;
	}
	public String getUrlMoodle() {
		return urlMoodle;
	}
	public void setUrlMoodle(String urlMoodle) {
		this.urlMoodle = urlMoodle;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String toStringQueryInsert() {
		// TODO Auto-generated method stub
		return "\""+title+"\",\" "+urlMoodle+ "\",\""+token+"\"";
	}
	
	

	@Override
	public String toString() {
		return "title=\"" + title + "\", urlMoodle=\"" + urlMoodle
				+ "\", token=\"" + token + "\", id=" + id;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Department d = (Department) obj;
		if(this.getTitle().equalsIgnoreCase(d.getTitle()) &&
				this.getUrlMoodle().equalsIgnoreCase(d.getUrlMoodle())){return true;}
		return false;
	}
	
	
	
	
}

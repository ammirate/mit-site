package it.unisa.offerta_formativa.beans;

public class Cycle {
	private int id;
	private String title;
	
	public Cycle(int id, String title){
		if(id<=0)throw new IllegalArgumentException("id cannot be less than 1");
		if(title.equalsIgnoreCase("")) throw new IllegalArgumentException("title cannot be void");
		this.id=id;
		this.title=title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id<=0)throw new IllegalArgumentException("id cannot be less than 1");
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title.equalsIgnoreCase("")) throw new IllegalArgumentException("title cannot be void");
		this.title = title;
	}
	
	public String getInsertQuery(){
		return id+" , "+title;
	}

	@Override
	public String toString() {
		return "id=" + id + ", title=" + title;
	}
	
	
	
	
}

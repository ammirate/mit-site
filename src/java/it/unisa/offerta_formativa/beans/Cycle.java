package it.unisa.offerta_formativa.beans;

public class Cycle {
	private int number;
	private String title;
	
	public Cycle(int id, String title){
		if(id<=0)throw new IllegalArgumentException("id cannot be less than 1");
		if(title.equalsIgnoreCase("")) throw new IllegalArgumentException("title cannot be void");
		this.number=id;
		this.title=title;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		if(number<=0)throw new IllegalArgumentException("id cannot be less than 1");
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title.equalsIgnoreCase("")) throw new IllegalArgumentException("title cannot be void");
		this.title = title;
	}
	
	public String getInsertQuery(){
		return number+" , "+title;
	}

	@Override
	public String toString() {
		return "id=" + number + ", title=" + title;
	}
	
	
	
	
}

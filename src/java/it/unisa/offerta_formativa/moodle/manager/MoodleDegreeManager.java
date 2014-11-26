package it.unisa.offerta_formativa.moodle.manager;

import java.io.UnsupportedEncodingException;

import it.unisa.offerta_formativa.moodle.moodlerest.*;

/**
 * 
 * @author Davide
 *
 */
public class MoodleDegreeManager {
	
	/**
	 * instance of this class, obtainable only by @getInstance()
	 */
	private static MoodleDegreeManager instance = null;
	
	private MoodleDegreeManager(){
		MoodleCallRestWebService.init("http://localhost/moodle/webservice/rest/server.php", "2c6f2faa8aac80fc912cff9f6f8bad66", false);
		//LINEA PER ALESSANDRO: MoodleCallRestWebService.init("http://localhost/moodle/webservice/rest/server.php", "d5f0d0c6c4e01b46ace2acd538d9ddad", false);
	}
	
	/**
	 * Singleton Pattern
	 * @return the unique instance of this class
	 */
	public static MoodleDegreeManager getInstance(){
		if(instance == null){
			instance = new MoodleDegreeManager();
		}
		return instance;
	}
	
	/**
	 * Create Degree Category into the Moodle Database
	 * @param title is the title of Degree to be inserted
	 * @param cycle is the id of parent into the Moodle Database
	 * @return true if all goes well
	 */
	public boolean createDegree(String title, int parent) {
		try {
			MoodleRestCourse.createCategory(new MoodleCategory(title, Long.parseLong(String.valueOf(parent))));
			return true;
		} catch (UnsupportedEncodingException | MoodleRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// TODO Auto-generated method stub
	}

	/**
	 * Delete Degree Category into the Moodle Database
	 * @param title is the title of Degree to be deleted
	 * @param cycle is the id of Degree Category into the Moodle Database
	 * @return true if all goes well
	 */
	public boolean deleteDegree(String title, int cycle) {
		try {
			MoodleRestCourse.deleteCategory(new MoodleCategory(Long.parseLong(String.valueOf(cycle)), title));
			return true;
		} catch (UnsupportedEncodingException | MoodleRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Update Degree Category into the Moodle Database
	 * @param title is the title of Degree to be inserted
	 * @param cycle is the id of Degree Category into the Moodle Database
	 * @return true if all goes well
	 */
	public boolean updateDegree(String title, int cycle) {
		try {
			MoodleRestCourse.updateCategory(new MoodleCategory(Long.parseLong(String.valueOf(cycle)), title));
			return true;
		} catch (UnsupportedEncodingException | MoodleRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Get Id of Degree by name
	 * @param name
	 * @return Id or -1 if doesn't exists
	 */
	public int getIdDegree(String name) {
		Long id = (long) 0;
		try {
			MoodleCategory[] categories = MoodleRestCourse.getCategories();
			for(int i=0;i<categories.length;i++){
				if(categories[i].getName().equalsIgnoreCase(name)) id=categories[i].getId();
			}
			return Integer.parseInt(String.valueOf(id));
		} catch (UnsupportedEncodingException | MoodleRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		// TODO Auto-generated method stub
	}


}

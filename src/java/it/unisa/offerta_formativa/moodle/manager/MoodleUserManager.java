package it.unisa.offerta_formativa.moodle.manager;

import it.unisa.offerta_formativa.moodle.moodle_rest.UserFieldSearch;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleCallRestWebService;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleRestException;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleRestUser;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleUser;
import java.io.UnsupportedEncodingException;


/**
 * 
 * @author Davide
 *
 */
public class MoodleUserManager {

	/**
	 * instance of this class, obtainable only by @getInstance()
	 */
	private static MoodleUserManager instance = null;
	
	private MoodleUserManager(String urlMoodle,String token){
		MoodleCallRestWebService.init(urlMoodle+"/webservice/rest/server.php", token, false);
	}
	
	/**
	 * Singleton Pattern
	 * @return the unique instance of this class
	 */
	public static MoodleUserManager getInstance(String urlMoodle,String token){
		if(instance == null){
			instance = new MoodleUserManager(urlMoodle, token);
		}
		return instance;
	}
	
	/**
	 * Create User into the Moodle Database
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return true if all goes well
	 */
	public boolean createUser(String username, String password, String firstname, String lastname, String email){
		try {
			MoodleUser user=new MoodleUser(username, password,firstname, lastname, email);
			MoodleRestUser.createUser(user);
			return true;
		} catch (MoodleRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// TODO Auto-generated method stub
	}
	
	/**
	 * Get Id of user by username
	 * @param username
	 * @return Id or -1 if doesn't exists
	 */
	public int getIdByUsername(String username){
		try {
			UserFieldSearch fieldSearch = UserFieldSearch.USERNAME;
			String user[] = {username};
			MoodleUser[] users=MoodleRestUser.getUsersByField(fieldSearch.USERNAME, user);
			int iduser=Integer.parseInt(String.valueOf(users[0].getId())) ;
			return iduser;
		} catch (MoodleRestException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		// TODO Auto-generated method stub
	}
}

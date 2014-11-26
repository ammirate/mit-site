package it.unisa.offerta_formativa.moodle.manager;

import java.io.UnsupportedEncodingException;

import it.unisa.offerta_formativa.moodle.moodlerest.*;

/**
 * 
 * @author Davide
 *
 */
public class MoodleTeachingManager {

	/**
	 * instance of this class, obtainable only by @getInstance()
	 */
	private static MoodleTeachingManager instance = null;
	
	private MoodleTeachingManager(String urlMoodle,String token){
		MoodleCallRestWebService.init(urlMoodle, token, false);
	}
	
	/**
	 * Singleton Pattern
	 * @return the unique instance of this class
	 */
	public static MoodleTeachingManager getInstance(String urlMoodle,String token){
		if(instance == null){
			instance = new MoodleTeachingManager(urlMoodle, token);
		}
		return instance;
	}
	
	/**
	 * Create Teaching into the Moodle Database
	 * @param shortname of course
	 * @param fullname of course
	 * @param categoryid of category where the course will be inserted
	 * @return Id or -1 if doesn't exists
	 */
	public int createTeaching(String shortname, String fullname, int categoryid) {
		try {
			MoodleCourse course = MoodleRestCourse.createCourse(new MoodleCourse(shortname,fullname,Long.parseLong(String.valueOf(categoryid))));
			return Integer.parseInt(String.valueOf(course.getId()));
		} catch (UnsupportedEncodingException | MoodleRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		// TODO Auto-generated method stub
	}
	
	/**
	 * Assign teacher to course in Moodle
	 * @param userId
	 * @param courseId
	 * @return true if all goes well
	 */
	public boolean assignTeacher(int userId, int courseId) {
		try {
			MoodleUserEnrolment[] enrolment={new MoodleUserEnrolment(3,Long.parseLong(String.valueOf(userId)),Long.parseLong(String.valueOf(courseId)))};
			MoodleRestUserEnrolment.enrolUsers(enrolment);
			return true;
		} catch (MoodleRestException | MoodleRestUserEnrolmentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// TODO Auto-generated method stub
	}
}

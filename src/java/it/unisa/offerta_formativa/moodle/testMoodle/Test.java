package it.unisa.offerta_formativa.moodle.testMoodle;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.management.relation.RoleInfo;

import it.unisa.offerta_formativa.moodle.moodlerest.*;

public class Test {

	public static void main(String argv[]) throws MoodleRestCourseException, UnsupportedEncodingException, MoodleRestException, MoodleUserRoleException, MoodleRestUserEnrolmentException{
		MoodleCallRestWebService.init("http://localhost/moodle/webservice/rest/server.php", "2c6f2faa8aac80fc912cff9f6f8bad66", false);
		
		
		
		//test per moodle
		
		 /**
		// Inserimento corsi 
		MoodleCourse corso = new MoodleCourse("MAl","Management Alex",3);
		
		MoodleCourse corsoottenuto = MoodleRestCourse.createCourse(corso);
		
		//MoodleCourse[] corsi=MoodleRestCourse.getAllCourses();
		//Long idcorso = corsi[1].getId();
		
		Long idcorso = corsoottenuto.getId();
		//System.out.println(idcorso+ " "+corsi[1].getFullname());
		
		UserFieldSearch fieldSearch = UserFieldSearch.USERNAME;
		
		String users[] = {"davide"};
		
		
		MoodleUser[] utenti=MoodleRestUser.getUsersByField(fieldSearch.USERNAME, users);
		Long idutente=utenti[0].getId();
		
		System.out.println(idutente + " "+utenti[0].getFullname());
		
				
		MoodleUserEnrolment[] permesso={new MoodleUserEnrolment(3,idutente,idcorso)};
		
		MoodleRestUserEnrolment.enrolUsers(permesso);
		**/
		
		/**
		MoodleCalendar calendario= new MoodleCalendar();
		
		MoodleCalendar[] eventi = MoodleRestCalendar.getCalendarEvents(calendario, 1, 1, 0L, 0L, 0);
		
		
		if (eventi[0]==null) System.out.println("niente eventi");
		else{
		Date d = new Date(eventi[0].getTimestart());
		
		System.out.println(d);
		}**/
		
		//MoodleRestCourse.updateCategory(new MoodleCategory(2, "Primo Ciclo"));
		// crea utente 
		// MoodleUser prova=new MoodleUser("davide", "Moodle_123","Davide", "De Chiara", "dade@gmail.com");
		// MoodleRestUser.createUser(prova);
		
		
		//MoodleRestCourse.createCategory(new MoodleCategory("provapars", Long.parseLong(String.valueOf(2))));

		// crea categoria
		 MoodleRestCourse.createCategory(new MoodleCategory(2, "Triennale"));
		 MoodleRestCourse.createCategory(new MoodleCategory(3, "Magistrale"));
		 MoodleRestCourse.createCategory(new MoodleCategory(4, "Dottorato"));
		
		//subcategoria
		// MoodleRestCourse.createCategory(new MoodleCategory("prova", 3L)); 
		
		// inserisci corsi dentro categoria
		// MoodleRestCourse.createCourse(new MoodleCourse("IS","Ingegneria del software",2));
		// MoodleRestCourse.createCourse(new MoodleCourse("ITPSM","IT project and service management",3));
		// MoodleRestCourse.createCourse(new MoodleCourse("ACG","Advanced course for genius",4));
		
		
		
	}
}

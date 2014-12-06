/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.moodle.prove;

import it.unisa.offerta_formativa.moodle.moodle_rest.*;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Davide
 */
public class Eventi {

	public static void main(String argv[]) throws MoodleRestException, UnsupportedEncodingException {
		MoodleCallRestWebService.init("http://localhost/moodle/webservice/rest/server.php", "2c6f2faa8aac80fc912cff9f6f8bad66", false);
		
                Long id = null;
                MoodleCategory[] categori = MoodleRestCourse.getCategories();
			for(int i=0;i<categori.length;i++){
				if(categori[i].getName().equalsIgnoreCase("Information Technology and Management")) id=categori[i].getId();
		}
                        
		OptionParameter categorySearch = new OptionParameter("parent",id.toString());
                MoodleCategory[] categories = MoodleRestCourse.getCategories(categorySearch);
                
                for(int i=0;i<categori.length;i++){
				if(categori[i].getName().equalsIgnoreCase("2014/2015")) id=categori[i].getId();
		}
                
                System.out.println(id);
                
                MoodleRestCourse.createCourse(new MoodleCourse("prova","prova",id));
                
		//MoodleCalendar calendario = new MoodleCalendar();
                //calendario.setName("Calendario");
                //MoodleCalendar eventi = MoodleRestCalendar.createCalendarEvent(calendario);
			
                //System.out.println(eventi.getName());
                //Date d = new Date(eventi.getTimestart());
		//System.out.println(eventi.getDescription());

                        
		//System.out.println(d);
		//if (eventi==null) System.out.println("niente eventi");
		//else{
		//Date c = new Date(eventi.getTimestart());
		
		//System.out.println(d);
		//}
		
				
		
		
	}
}


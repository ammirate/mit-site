/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.moodle.prove;

import it.unisa.offerta_formativa.moodle.moodle_rest.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 *
 * @author Davide
 */
public class Eventi {

	public static void main(String argv[]) throws MoodleRestException, UnsupportedEncodingException {
		MoodleCallRestWebService.init("http://localhost/moodle/webservice/rest/server.php", "2c6f2faa8aac80fc912cff9f6f8bad66", false);
		
		
		MoodleCalendar calendario = new MoodleCalendar();
                calendario.setName("Calendario");
                MoodleCalendar eventi = MoodleRestCalendar.createCalendarEvent(calendario);
			
                System.out.println(eventi.getName());
                Date d = new Date(eventi.getTimestart());
		System.out.println(eventi.getDescription());

                        
		System.out.println(d);
		if (eventi==null) System.out.println("niente eventi");
		else{
		Date c = new Date(eventi.getTimestart());
		
		System.out.println(d);
		}
		
				
		
		
	}
}


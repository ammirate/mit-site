package manager;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import moodle.manager.MoodleDegreeManager;
import moodle.moodlerest.MoodleCallRestWebService;

/**
 * 
 * @author Davide
 *
 */
public class ParserHtmlManager {

	/**
	 * instance of this class, obtainable only by @getInstance()
	 */
	private static ParserHtmlManager instance = null;
	
	private ParserHtmlManager(){
		MoodleCallRestWebService.init("http://localhost/moodle/webservice/rest/server.php", "da78811c3a6c52452b5b50bdf570061e", false);
	}
	
	/**
	 * Singleton Pattern
	 * @return the unique instance of this class
	 */
	public static ParserHtmlManager getInstance(){
		if(instance == null){
			instance = new ParserHtmlManager();
		}
		return instance;
	}
	
	public String getHtml(String link){
		Document doc;
		Element didattica = null;
		try {
			doc = Jsoup.connect(link).get();
			didattica = doc.getElementById("column1of2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	    
	    return didattica.toString();
	}
	
}

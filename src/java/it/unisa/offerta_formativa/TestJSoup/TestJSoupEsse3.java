package TestJSoup;

import java.io.IOException;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class TestJSoupEsse3 {
 
  public static void main(String[] args) {
 
	Document doc;
	try {
 
		// need http protocol
		// doc = Jsoup.connect("https://esse3web.unisa.it/unisa/Guide/PaginaADErogata.do;jsessionid=2138B215C22E5238EE45BA2DD2C3CCB9.jvm6?ad_er_id=2014*N0*N0*S1*39751*509790&ANNO_ACCADEMICO=2014&mostra_percorsi=N").get();
		//Element didattica = doc.getElementById("column1of2");
 
		doc = Jsoup.connect("https://esse3web.unisa.it/unisa/Guide/PaginaADErogata.do;jsessionid=2138B215C22E5238EE45BA2DD2C3CCB9.jvm6?ad_er_id=2014*N0*N0*S1*39751*509790&ANNO_ACCADEMICO=2014&mostra_percorsi=N").get();
	    Element didattica = doc.getElementById("column1of2");
	 
		
		System.out.println(didattica);
		
	} catch (IOException e) {
		e.printStackTrace();
	}
 
  }
 
}

package findmycollege2.velo.data;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import findmycollege2.velo.scapers.Scraper;

// TODO: Auto-generated Javadoc
/**
 * The Class Index.
 */
public class Index {
	
	/** The origin. */
	private String url, xPath, origin;
	
	/**
	 * Instantiates a new index.
	 *
	 * @param url the url
	 * @param generalXPath the general X path
	 */
	public Index(String url, String generalXPath) {
		this.url = url;		
		this.xPath = generalXPath;
	}
	
	/**
	 * Instantiates a new index.
	 *
	 * @param url the url
	 * @param og the og
	 * @param generalXPath the general X path
	 */
	public Index(String url, String og, String generalXPath) {
		this.url = url;		
		this.xPath = generalXPath;
		this.origin = og;
	}
	
	/**
	 * Gets the all links.
	 *
	 * @return the all links
	 */
	public String[] getAllLinks() {
		Scraper s = new Scraper();
		HtmlPage page = s.scrape(this.url);
		List<Object> links = page.getByXPath(this.xPath);
		String[] out = new String[links.size()];
		ArrayList<String> outAL = new ArrayList<String>();		
		Integer i = 0;
		for(Object l : links) {
			String a = ((HtmlAnchor)l).getAttribute("href").trim();
			if(a.contains("http")) {
				
			}
			else {
				a = this.origin + a;
			}
			if(a.length() > this.origin.length()) {
				outAL.add(a);
			}
			System.out.println(a);
			i+=1;
		}
		String[] o = new String[outAL.size()];
		Integer ii = 0;
		for(String oi : outAL) {
			o[ii] = oi;
			ii+=1;
		}
		return o;
	}
}

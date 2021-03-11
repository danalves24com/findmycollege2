package findmycollege2.velo.scapers;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import findmycollege2.velo.data.Page;
import findmycollege2.velo.data.Piece;
import findmycollege2.velo.data.Profile;

public class PageScraper {
	private Page page;

	public void loadPage(Page page) {
		this.page = page;
	}

	private Profile profile;

	public Profile getProfile() {
		return null;
	}

	private String trimmer(String in) {
		String aug = in;
		aug.trim();
		return aug;
	}

	public void beginScrape() {

		Scraper scraper = new Scraper();
		System.out.println("og: " + this.page.origin());
		HtmlPage page = scraper.scrape(this.page.origin());
		
		for (Piece p : this.page.getAllPieces()) {
			String val = ((HtmlElement) page.getByXPath(p.getLocationOnPage()).get(0)).asText();
			p.setValue(val);
			System.out.println(val);
		}

	}

}

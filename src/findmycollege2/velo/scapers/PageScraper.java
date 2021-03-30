package findmycollege2.velo.scapers;

import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import findmycollege2.velo.data.DataType;
import findmycollege2.velo.data.Page;
import findmycollege2.velo.data.Piece;
import findmycollege2.velo.data.Profile;

public class PageScraper {
	private Page page;
	private ArrayList<Profile> profiles = new ArrayList<Profile>();

	public void loadPage(Page page) {
		this.page = page;
	}

	private Profile profile = new Profile();

	public Profile getProfile() {
		return this.profile;
	}

	public void reset() {
		this.profile = new Profile();
		this.page = null;
		this.HTMLpage.cleanUp();
	}

	private String trimmer(String in) {
		String aug = in;		
		aug.trim();
		return aug;
	}

	public HtmlPage getHTMLPage() {
		return this.HTMLpage;
	}

	public Scraper scraper = new Scraper();
	private HtmlPage HTMLpage = null;

	public void beginScrape() {

		System.out.println("og: " + this.page.origin());
		HTMLpage = scraper.scrape(this.page.origin());
		String name = "";
		for (Piece p : this.page.getAllPieces()) {
			try {
				String val = ((HtmlElement) HTMLpage.getByXPath(p.getLocationOnPage()).get(0)).getTextContent();
				p.setValue(this.trimmer(val));
				this.profile.addData(p);
				if (p.getDataType().equals(DataType.NAME)) {
					name = val;
				}
			} catch (Exception e) {
				System.out.println("Error with " + p.getDataType());
				e.printStackTrace();
			}
		}
		System.out.println("generating profile");
		// build
		this.profile.setName(name);
		// this.profile.print();
		// append and upload
		// this.profile.uploadSelf();
		// this.profiles.add(this.profile);
		// reset
		// this.profile = new Profile();
	}

}

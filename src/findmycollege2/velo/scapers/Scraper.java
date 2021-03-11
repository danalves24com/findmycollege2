package findmycollege2.velo.scapers;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Scraper {
	private WebClient client = new WebClient();

	public Scraper() {
		client.getOptions().setJavaScriptEnabled(true);
		client.getOptions().setCssEnabled(true);
		client.getOptions().setThrowExceptionOnScriptError(false);
	}
	
	public HtmlPage scrape(String uri) {
		try {
			return this.client.getPage(uri);
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}

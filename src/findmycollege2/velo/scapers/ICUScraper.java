package findmycollege2.velo.scapers;

import java.util.ArrayList;

import findmycollege2.velo.data.DataType;
import findmycollege2.velo.data.Index;
import findmycollege2.velo.data.Page;
import findmycollege2.velo.data.Piece;

public class ICUScraper extends PageScraper {

	public ICUScraper() {

	}

	private Page page;

	public void run() {

		DataType[] types = { DataType.NAME, DataType.ACCEPTANCE_RATE, DataType.LOCATION };
		String[] location = { "/html/body/div[3]/div[4]/div[1]/div/div[2]/table/tbody/tr[1]/td/a/span/strong",
				"//example[contains(text(), '%')]", "/html/body/div[3]/div[4]/div[2]/div[2]/div[2]/table/tbody/tr[1]/td" };

		Index in = new Index("https://www.4icu.org/us/a-z/", "https://www.4icu.org", "//tbody//a");
		for (String l : in.getAllLinks()) {
			if (l.length() > 1 && l != null) {
				this.page = new Page(l);
				Integer i = 0;
				for (DataType type : types) {
					Piece p = new Piece(type, location[i]);
					page.add(p);
					i += 1;
				}
				super.loadPage(page);
				super.beginScrape();
			}

		}

	}

}

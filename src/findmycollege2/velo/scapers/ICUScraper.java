package findmycollege2.velo.scapers;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlItalic;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlUnorderedList;

import findmycollege2.velo.conns.Database;
import findmycollege2.velo.data.DataType;
import findmycollege2.velo.data.Element;
import findmycollege2.velo.data.Index;
import findmycollege2.velo.data.Page;
import findmycollege2.velo.data.Piece;
import findmycollege2.velo.data.Profile;
import net.sourceforge.htmlunit.cyberneko.HTMLComponent;

public class ICUScraper extends PageScraper {

	public ICUScraper() {

	}

	private Page page;

	public void run() {
		Element[] sources = {
				new Element(DataType.NAME,
						"/html/body/div[3]/div[4]/div[1]/div/div[2]/table/tbody/tr[1]/td/a/span/strong"),
				new Element(DataType.ACCEPTANCE_RATE,
						"/html/body/div[3]/div[7]/div[2]/div/div[2]/table/tbody/tr[contains(.,'Admission Rate')]/td"),
				new Element(DataType.LOCATION, "/html/body/div[3]/div[4]/div[2]/div[2]/div[2]/table/tbody/tr[1]/td"),
				new Element(DataType.INTERNATIONAL_UNDERGRAD_COST,
						"/html/body/div[3]/div[6]/div[2]/div/table/tbody/tr[2]/td[2]/strong"),
				new Element(DataType.INTERNATIONAL_POSTGRAD_COST,
						"/html/body/div[3]/div[6]/div[2]/div/table/tbody/tr[2]/td[3]/strong"),
				new Element(DataType.STUDENT_ENROLLMENT,
						"/html/body/div[3]/div[8]/div[2]/div/div[1]/table/tbody/tr[1]/td/strong"),
				new Element(DataType.TYPE, "/html/body/div[3]/div[8]/div[2]/div/div[1]/table/tbody/tr[3]/td"),
				new Element(DataType.RELOGIOUS_AFFILIATION,
						"/html/body/div[3]/div[8]/div[2]/div/div[2]/table/tbody/tr[3]/td"),
				new Element(DataType.REQUIREMENTS,
						"/html/body/div[3]/div[7]/div[2]/div/div[2]/table/tbody/tr[contains(.,'Admission Requirements')]/td"),
				new Element(DataType.LIBRARY,
						"/html/body/div[3]/div[10]/div[2]/div/div[1]/table/tbody/tr[contains(.,'Library')]/td"),
				new Element(DataType.SELECTION_TYPE,
						"/html/body/div[3]/div[7]/div[2]/div/div[2]/table/tbody/tr[contains(.,'Selection Type')]/td"),
				new Element(DataType.GENDER,
						"/html/body/div[3]/div[7]/div[2]/div/div[2]/table/tbody/tr[contains(.,'Gender')]/td"),
				new Element(DataType.INTERNATIONAL,
						"/html/body/div[3]/div[7]/div[2]/div/div[2]/table/tbody/tr[contains(.,'International Students')]/td"),
				new Element(DataType.HOUSING,
						"/html/body/div[3]/div/div/div/div[1]/table/tbody/tr[contains(.,'Housing')]/td"),
				new Element(DataType.FINANCIAL_AID,
						"/html/body/div[3]/div/div/div/div[1]/table/tbody/tr[contains(.,'Financial Aids')]/td"),
				new Element(DataType.STUDY_ABROAD,
						"/html/body/div[3]/div/div/div/div[2]/table/tbody/tr[contains(.,'Study Abroad')]/td"),
				new Element(DataType.DISTANCE_LEARNING,
						"/html/body/div[3]/div/div/div/div[2]/table/tbody/tr[contains(.,'Distance Learning')]/td") };

		String[] cc = { "CZ", "US", "AL", "DZ", "AS", "AD", "AO", "AI", "AQ", "AG", "AR", "AM", "AW", "AU", "AT", "AZ",
				"BS", "BH", "BD", "BB", "BY", "BE", "BZ", "BJ", "BM", "BT", "BO", "BQ", "BA", "BW", "BV", "BR", "IO",
				"UM", "VG", "VI", "BN", "BG", "BF", "BI", "KH", "CM", "CA", "CV", "KY", "CF", "TD", "CL", "CN", "CX",
				"CC", "CO", "KM", "CG", "CD", "CK", "CR", "HR", "CU", "CW", "CY", "AX", "DK", "DJ", "DM", "DO", "EC",
				"EG", "SV", "GQ", "ER", "EE", "ET", "FK", "FO", "FJ", "FI", "FR", "GF", "PF", "TF", "GA", "GM", "GE",
				"DE", "GH", "GI", "GR", "GL", "GD", "GP", "GU", "GT", "GG", "GN", "GW", "GY", "HT", "HM", "VA", "HN",
				"HK", "HU", "IS", "IN", "ID", "CI", "IR", "IQ", "IE", "IM", "IL", "IT", "JM", "JP", "JE", "JO", "KZ",
				"KE", "KI", "KW", "KG", "LA", "LV", "LB", "LS", "LR", "LY", "LI", "LT", "LU", "MO", "MK", "MG", "MW",
				"MY", "MV", "ML", "MT", "MH", "MQ", "MR", "MU", "YT", "MX", "FM", "MD", "MC", "MN", "ME", "MS", "MA",
				"MZ", "MM", "NA", "NR", "NP", "NL", "NC", "NZ", "NI", "NE", "NG", "NU", "NF", "KP", "MP", "NO", "OM",
				"PK", "PW", "PS", "PA", "PG", "PY", "PE", "PH", "PN", "PL", "PT", "PR", "QA", "XK", "RE", "RO", "RU",
				"RW", "BL", "SH", "KN", "LC", "MF", "PM", "VC", "WS", "SM", "ST", "SA", "SN", "RS", "SC", "SL", "SG",
				"SX", "SK", "SI", "SB", "SO", "ZA", "GS", "KR", "SS", "ES", "LK", "SD", "SR", "SJ", "SZ", "SE", "CH",
				"SY", "TW", "TJ", "TZ", "TH", "TL", "TG", "TK", "TO", "TT", "TN", "TR", "TM", "TC", "TV", "UG", "UA",
				"AE", "GB", "AF", "UY", "UZ", "VU", "VE", "VN", "WF", "EH", "YE", "ZM", "ZW" };
		Database db = new Database();
		for (String ccc : cc) {
			try {

				Index in = new Index("https://www.4icu.org/" + ccc.toLowerCase() + "/a-z/", "https://www.4icu.org",
						"//tbody//a");
				for (String l : in.getAllLinks()) {
					if (!db.sourceExists(l)) {
						try {

							if (l.length() > 1 && l != null) {
								this.page = new Page(l);
								for (Element e : sources) {
									Piece p = new Piece(e.getType(), e.path());
									page.add(p);
								}
								super.loadPage(page);
								super.beginScrape();
								Profile profile = super.getProfile();
								HtmlPage htmlPage = super.getHTMLPage();
								ArrayList<String> orientation = new ArrayList<String>(),
										potentialClasses = new ArrayList<String>();
								String[] oriDict = { "Arts & Humanities", "Business & Social Sciences",
										"Language & Cultural", "Medicine & Health", "Engineering",
										"Science & Technology" };
								List<Object> classes = htmlPage
										.getByXPath("/html/body/div/div[5]/div/div/div/div/div/ul");
								Integer row = 1;
								for (Object tr : htmlPage
										.getByXPath("/html/body/div[3]/div[5]/div[2]/div[1]/table/tbody/tr")) {
									HtmlTableRow tr1 = (HtmlTableRow) tr;
									List<Object> td = htmlPage.getByXPath(
											"/html/body/div[3]/div[5]/div[2]/div[1]/table/tbody/tr[" + row + "]/td/i");
									Boolean judged = false;
									for (Object i : td) {
										HtmlItalic i1 = (HtmlItalic) i;
										if (i1.getAttribute("class").contains("d1") && !judged) {
											orientation.add(oriDict[row - 1]);
											Integer pos = (6 + (row - 1));
											String path = "/html/body/div[3]/div[5]/div[2]/div[" + pos
													+ "]/div/div/div[2]/ul/li";
											System.out.println(path);
											for (Object li : htmlPage.getByXPath(path)) {
												HtmlListItem li1 = (HtmlListItem) li;
												potentialClasses.add(li1.getTextContent().trim());
											}
											judged = true;
										}
									}
									// HtmlTableDataCell
									row += 1;
								}
								String orientations = String.join(", ", orientation),
										classess = String.join(", ", potentialClasses);
								Piece dat = new Piece(DataType.ORIENTATION, "none"),
										dat_classess = new Piece(DataType.POTENTIAL_CLASSES, "none"),
										source = new Piece(DataType.SOURCE, "none"),
										web = new Piece(DataType.WEBSITE, "none");

								dat_classess.setValue(classess);
								dat.setValue(orientations);
								source.setValue(htmlPage.getUrl().toString());
								web.setValue(((HtmlAnchor) htmlPage.getByXPath(
										"/html/body/div[3]/div[4]/div[1]/div/div[2]/table/tbody/tr[1]/td/a").get(0))
												.getAttribute("href"));

								Piece[] pieces = { dat_classess, dat, source, web };

								for (Piece p : pieces) {
									profile.addData(p);
								}

								profile.print();
								profile.uploadSelf();
								super.reset();

							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}

package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import findmycollege2.velo.conns.Database;
import findmycollege2.velo.scapers.ICUScraper;
import org.junit.jupiter.api.Test;


class icuScrape {

	@Test
	void test() {
		Set<String> artifactoryLoggers = new HashSet<String>(
				Arrays.asList("org.apache.http", "groovyx.net.http", "com.gargoylesoftware.htmlunit"));
		for (String log : artifactoryLoggers) {
			ch.qos.logback.classic.Logger artLogger = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory
					.getLogger(log);
			artLogger.setLevel(ch.qos.logback.classic.Level.INFO);
			artLogger.setAdditive(false);
		}

		Database db = new Database("10.0.1.103", "root", "Saniroot", "edi");
		db.connect();
		ICUScraper sc = new ICUScraper();
		sc.run();
	}

}

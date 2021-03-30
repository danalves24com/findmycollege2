package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import findmycollege2.velo.conns.Database;
import findmycollege2.velo.conns.ServerEndpoints;
import findmycollege2.velo.data.Piece;
import findmycollege2.velo.data.Profile;
import findmycollege2.velo.scapers.ICUScraper;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		Set<String> artifactoryLoggers = new HashSet<String>(Arrays.asList("org.apache.http", "groovyx.net.http", "com.gargoylesoftware.htmlunit"));
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

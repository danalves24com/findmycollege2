package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import findmycollege2.velo.scapers.ICUScraper;

class testICU {

	@Test
	void test() {						
		ICUScraper sc = new ICUScraper();
		sc.run();
		
		
	}

}

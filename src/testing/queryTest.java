package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import findmycollege2.velo.conns.Database;
import findmycollege2.velo.data.Profile;

class queryTest {

	@Test
	void test() {
		Database db = new Database("10.0.1.103", "root", "Saniroot", "edi");
		db.connect();
		ArrayList<Object> objects = db.getAllObject();
		for(Object o : objects) {
			Profile p = (Profile)o;
			p.print();			
		}
	}

}

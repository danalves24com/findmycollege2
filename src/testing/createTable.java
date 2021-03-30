package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import findmycollege2.velo.conns.Database;

class createTable {

	@Test
	void test() {
		Database db = new Database("10.0.1.103", "root", "Saniroot", "edi");
		db.connect();
		db.updateCoreTable();
	}

}

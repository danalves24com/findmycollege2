package findmycollege2.velo.data;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import findmycollege2.velo.conns.Database;

/**
 * The Class Profile.
 */
public class Profile implements Serializable {
	private ArrayList<Piece> data = new ArrayList<Piece>();
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addData(Piece dat) {
		this.data.add(dat);
	}
	
	public void print() {
		for(Piece p : data) {
			System.out.println(p.getDataType() + " = " + p.getValue());
		}		
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Piece> getData() {
		return this.data;
	}
	
	public void uploadSelf() {
		Database db = new Database();
		db.addProfile(this);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

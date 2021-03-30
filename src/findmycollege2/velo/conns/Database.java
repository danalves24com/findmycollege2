package findmycollege2.velo.conns;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.assertj.core.util.Arrays;

import findmycollege2.velo.data.DataType;
import findmycollege2.velo.data.Piece;
import findmycollege2.velo.data.Profile;

// TODO: Auto-generated Javadoc
/**
 * The Class Database.
 */
public class Database {

	/** The con. */
	private static Connection con;

	/** The db name. */
	private String url, uname, pass, dbName;

	/**
	 * Instantiates a new database.
	 */
	public Database() {
	}

	/**
	 * Instantiates a new database.
	 *
	 * @param url    the url
	 * @param uname  the uname
	 * @param pass   the pass
	 * @param dbName the db name
	 */
	public Database(String url, String uname, String pass, String dbName) {
		this.url = url;
		this.uname = uname;
		this.pass = pass;
		this.dbName = dbName;
	}

	public Blob serialize(Object obj) {
		ByteArrayOutputStream baos;
		ObjectOutputStream out;
		baos = new ByteArrayOutputStream();
		try {
			out = new ObjectOutputStream(baos);
			out.writeObject(obj);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] byteObject = baos.toByteArray();
		Blob blob = null;
		try {
			blob = new javax.sql.rowset.serial.SerialBlob(byteObject);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blob;
	}

	private Object deserialize(Blob blob) {
		byte[] bytes = null;
		try {
			bytes = blob.getBytes(1l, (int) blob.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteArrayInputStream bais;
		ObjectInputStream in;
		try {
			bais = new ByteArrayInputStream(bytes);
			in = new ObjectInputStream(bais);
			Object out = in.readObject();
			in.close();
			return out;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public ArrayList<Object> getAllObject() {
		ArrayList<Object> objects = new ArrayList<Object>();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT * FROM blobs where";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Blob b = rs.getBlob(2);
				objects.add(deserialize(b));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objects;
	}

	public Object getObject(String name) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT * FROM blobs where Name='" + name + "'";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Blob b = rs.getBlob(2);
				System.out.println(b);
				return deserialize(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Adds the object.
	 *
	 * @param o the o
	 * @throws SQLException the SQL exception
	 */
	public void addObject(Object o) throws SQLException {

		Blob b;
		try {
			b = serialize(o);
			Statement stmt = con.createStatement();
			String rGanTz = "insert into blobs (Name, data) values (?, ?)";

			PreparedStatement ps = con.prepareStatement(rGanTz);
			ps.setString(1, ((Profile) o).getName());
			ps.setBlob(2, b);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateCoreTable() {
		String queryCols = "DESCRIBE colleges;";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> cols = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery(queryCols);

			while (rs.next()) {
				cols.add(rs.getString(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		for (DataType type : DataType.values()) {
			if (!cols.contains(type.toString())) {
				System.out.println("adding col " + type);
				String sqlUpdate = "ALTER TABLE colleges ADD " + type + " LONGTEXT;";
				try {
					stmt.executeUpdate(sqlUpdate);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void createCoreProfileTable() {
		DataType[] cols = DataType.values();
		String[] cols_sql = new String[cols.length];
		for (Integer i = 0; i < cols.length; i += 1) {
			cols_sql[i] = cols[i].toString() + " LONGTEXT";
		}
		String allCols = String.join(", ", cols_sql);
		System.out.println(allCols);
		try {
			Statement stmt = con.createStatement();
			String sql = "CREATE TABLE colleges (" + allCols + ");";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean sourceExists(String src) {
		String queryCols = "select * from colleges where SOURCE ='" + src + "'";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ResultSet rs = stmt.executeQuery(queryCols);			
			Integer p = 0;
			while (rs.next()) {
				System.out.println();				
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

	public void addProfile(Profile prof) {

		DataType[] cols = DataType.values();
		String[] cols_sql = new String[cols.length], placeholders = new String[cols.length];

		for (Integer i = 0; i < cols.length; i += 1) {
			cols_sql[i] = cols[i].toString();
			placeholders[i] = "?";
		}
		String allCols = String.join(", ", cols_sql);
		String allPlaceholders = String.join(", ", placeholders);

		try {
			Statement stmt = con.createStatement();
			String rGanTz = "insert into colleges (" + allCols + ") values (" + allPlaceholders + ")";
			PreparedStatement ps = con.prepareStatement(rGanTz);
			Integer iter = 1;
			ArrayList<DataType> c = new ArrayList<DataType>();
			Collections.addAll(c, cols);
			Integer i = 1;
			for (DataType col : cols) {
				ps.setString(i, null);
				i += 1;
			}
			for (Piece p : prof.getData()) {
				ps.setString(c.indexOf(p.getDataType()) + 1, p.getValue());
				iter += 1;
			}
			System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Close.
	 */
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Connect.
	 */
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + this.url + ":3306/" + this.dbName, this.uname,
					this.pass);
			System.out.println("connected to database");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

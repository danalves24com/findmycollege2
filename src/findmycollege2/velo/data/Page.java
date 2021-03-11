package findmycollege2.velo.data;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map.Entry;

import findmycollege2.velo.data.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Page.
 */
public class Page {
	
	/**
	 * Instantiates a new page.
	 *
	 * @param url the url
	 */
	public Page(String url) {
		this.page = url;
		
	}	
	
	/** The page. */
	private String page;
	
	/** The data. */
	private HashMap<DataType, Piece> data = new HashMap<DataType, Piece>();

	/**
	 * Origin.
	 *
	 * @return the string
	 */
	public String origin() {
		return page;
	}
	
	
	/**
	 * Adds the.
	 *
	 * @param p the p
	 */
	public void add(Piece p) {
		data.put(p.getDataType(), p);
	}

	/**
	 * Gets the piece.
	 *
	 * @param type the type
	 * @return the piece
	 */
	public Piece getPiece(DataType type) {
		return this.data.get(type);
	}

	/**
	 * Gets the all pieces.
	 *
	 * @return the all pieces
	 */
	public Piece[] getAllPieces() {
		Piece[] pieces = new Piece[this.data.size()];
		Integer i = 0;
		for (Entry<DataType, Piece> piece : data.entrySet()) {
			pieces[i] = piece.getValue();
			i += 1;
		}
		return pieces;
	}
}

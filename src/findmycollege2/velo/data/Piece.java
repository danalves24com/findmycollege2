package findmycollege2.velo.data;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Piece.
 */
public class Piece implements Serializable{

	/** The data type. */
	private DataType dataType;

	/** The location on page. */
	String locationOnPage;

	/** The value. */
	String value;

	/**
	 * Instantiates a new piece.
	 *
	 * @param dataType       the type of data being stored in this piece
	 * @param locationOnPage the location on page represented with an XPath
	 */
	public Piece(DataType dataType, String locationOnPage) {
		this.dataType = dataType;
		this.locationOnPage = locationOnPage;
	}

	/**
	 * Instantiates a new piece without any direct parameters
	 */
	public Piece() {

	}

	/**
	 * Gets the data type.
	 *
	 * @return dataType the type of data being stored in this piece
	 */
	public DataType getDataType() {
		return dataType;
	}

	/**
	 * Sets the data type.
	 *
	 * @param dataType the type of data being stored in this piece
	 */
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	/**
	 * Gets the location on page.
	 *
	 * @return the location on page
	 */
	public String getLocationOnPage() {
		return locationOnPage;
	}

	/**
	 * Sets the location on page.
	 *
	 * @param locationOnPage the new location on page
	 */
	public void setLocationOnPage(String locationOnPage) {
		this.locationOnPage = locationOnPage;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}

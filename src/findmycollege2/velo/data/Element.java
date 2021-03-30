package findmycollege2.velo.data;

public class Element {
	private DataType type;
	private String path;
	private Boolean condition;
	public Element(DataType type, String path) {
		this.type = type;
		this.path = path;		
	}	
	public DataType getType() {return this.type;}
	public String path() {return this.path;}
}

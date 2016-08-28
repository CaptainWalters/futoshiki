package futoshiki_puzzle;

// TODO: Auto-generated Javadoc
/**
 * The Class FutoshikiSquare.
 *
 * @author Oliver
 */
public class FutoshikiSquare {

	/** The value. */
	private int value;
	
	/** The x coord. */
	private int xCoord;
	
	/** The y coord. */
	private int yCoord;
	
	/**
	 * Instantiates a new futoshiki square.
	 *
	 * @param value the value
	 * @param xCoord the x coord
	 * @param yCoord the y coord
	 */
	public FutoshikiSquare(int value, int xCoord, int yCoord){
		this.value = value;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public int getValue(){
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(int value){
		this.value = value;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getx() {
		return xCoord;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int gety() {
		return yCoord;
	}

}

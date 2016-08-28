package futoshiki_puzzle;

// TODO: Auto-generated Javadoc
/**
 * The Class Constraint.
 */
public abstract class Constraint {

	/** The b. */
	private FutoshikiSquare a, b;
	
	/** The symbol. */
	protected String symbol;
	
	/**
	 * Instantiates a new constraint.
	 *
	 * @param a the a
	 * @param b the b
	 */
	public Constraint(FutoshikiSquare a, FutoshikiSquare b) {
		this.a = a;
		this.b = b;
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public String getSymbol(){
		return symbol;
	}
	
	/**
	 * Check.
	 *
	 * @param size the size
	 * @return true, if successful
	 */
	public abstract boolean check(int size);

	/**
	 * Gets the a.
	 *
	 * @return the a
	 */
	protected FutoshikiSquare getA() {
		return a;
	}
	
	/**
	 * Gets the b.
	 *
	 * @return the b
	 */
	protected FutoshikiSquare getB() {
		return b;
	}
	
}

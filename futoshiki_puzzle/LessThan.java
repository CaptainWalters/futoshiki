package futoshiki_puzzle;

// TODO: Auto-generated Javadoc
/**
 * The Class LessThan.
 */
public class LessThan extends Constraint {

	/**
	 * Instantiates a new less than.
	 *
	 * @param a the a
	 * @param b the b
	 */
	public LessThan(FutoshikiSquare a, FutoshikiSquare b) {
		super(a, b);
		if (a.getx() == b.getx()) {
			symbol = "<";
		} 
		else {
			symbol = "^";
		}
	}

	/* (non-Javadoc)
	 * @see futoshiki_puzzle.Constraint#check(int)
	 */
	@Override
	public boolean check(int size) {

		if ((getA().getValue()!=0) && (getB().getValue()!=0)) {
			return (getA().getValue() < getB().getValue());
		}
		else if((getA().getValue() == size) || (getB().getValue() == 1)) {
			return false;
		}
		else {
			return true;
		}
	}

}

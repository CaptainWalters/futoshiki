package futoshiki_puzzle;

// TODO: Auto-generated Javadoc
/**
 * The Class MoreThan.
 */
public class MoreThan extends Constraint {
	
	/**
	 * Instantiates a new more than.
	 *
	 * @param a the a
	 * @param b the b
	 */
	public MoreThan(FutoshikiSquare a, FutoshikiSquare b) {
		super(a, b);
		if (a.getx() == b.getx()) {
			symbol = ">";
		}
		else {
			symbol = "v";
		}
	}

	/* (non-Javadoc)
	 * @see futoshiki_puzzle.Constraint#check(int)
	 */
	@Override
	public boolean check(int size) {
		
		if ((getA().getValue() != 0) && (getB().getValue() != 0)) {
			return (getA().getValue() > getB().getValue());
		}
		else if((getA().getValue() == 1) || (getB().getValue() == size)) {
			return false;
		}
		else {
			return true;
		}
	}

}

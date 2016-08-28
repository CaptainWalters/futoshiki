package futoshiki_puzzle;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The Class FutoshikiPuzzleTest.
 */
public class FutoshikiPuzzleTest {

	/** The fp. */
	FutoshikiPuzzle FP = new FutoshikiPuzzle(5, true);
	
	/** The compare. */
	FutoshikiSquare compare[][] = new FutoshikiSquare[2][2];
	
	/**
	 * Size test.
	 */
	@Test
	public void sizeTest() {
		assertEquals(FP.getSize(), 5);
	}
	
	/**
	 * New square test.
	 */
	@Test
	public void newSquareTest(){
		FutoshikiSquare a = new FutoshikiSquare(5, 0, 0);
		assertEquals(a.getValue(), 5);
		assertEquals(a.getx(), 0);
		assertEquals(a.gety(), 0);
	}
	
	/**
	 * Square builder test.
	 */
	@Test
	public void squareBuilderTest() {
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				assertNotNull(FP.getGrid(i, j));
			}
		}
	}

	/**
	 * Value change test.
	 */
	@Test
	public void valueChangeTest() {
		compare[0][0] = new FutoshikiSquare(1, 0, 0);
		assertEquals(compare[0][0].getValue(), 1);
		compare[0][0].setValue(2);
		assertEquals(compare[0][0].getValue(), 2);
	}
	
	/**
	 * Less than row true check test.
	 */
	@Test
	public void lessThanRowTrueCheckTest() {
		compare[0][0] = new FutoshikiSquare(1, 0, 0);
		compare[0][1] = new FutoshikiSquare(2, 0, 1);
		Constraint test = new LessThan(compare[0][0], compare[0][1]);
		assertTrue(test.check(2));
	}
	
	/**
	 * Less than column true check test.
	 */
	@Test
	public void lessThanColumnTrueCheckTest() {
		compare[0][0] = new FutoshikiSquare(1, 0, 0);
		compare[1][0] = new FutoshikiSquare(2, 1, 0);
		Constraint test = new LessThan(compare[0][0], compare[1][0]);
		assertTrue(test.check(2));
	}
	 
	/**
	 * Less than row false check test.
	 */
	@Test
	public void lessThanRowFalseCheckTest() {
		compare[0][0] = new FutoshikiSquare(2, 0, 0);
		compare[0][1] = new FutoshikiSquare(1, 0, 1);
		Constraint test = new LessThan(compare[0][0], compare[0][1]);
		assertFalse(test.check(2));
	}
	
	/**
	 * Less than column false check test.
	 */
	@Test
	public void lessThanColumnFalseCheckTest() {
		compare[0][0] = new FutoshikiSquare(2, 0, 0);
		compare[1][0] = new FutoshikiSquare(1, 1, 0);
		Constraint test = new LessThan(compare[0][0], compare[1][0]);
		assertFalse(test.check(2));
	}
	
	/**
	 * Greater than row true check test.
	 */
	@Test
	public void greaterThanRowTrueCheckTest() {
		compare[0][0] = new FutoshikiSquare(2, 0, 0);
		compare[0][1] = new FutoshikiSquare(1, 0, 1);
		Constraint test = new MoreThan(compare[0][0], compare[0][1]);
		assertTrue(test.check(2));
	}
	
	/**
	 * Greater than column true check test.
	 */
	@Test
	public void greaterThanColumnTrueCheckTest() {
		compare[0][0] = new FutoshikiSquare(2, 0, 0);
		compare[1][0] = new FutoshikiSquare(1, 1, 0);
		Constraint test = new MoreThan(compare[0][0], compare[1][0]);
		assertTrue(test.check(2));
	}
	 
	/**
	 * Greater than row false check test.
	 */
	@Test
	public void greaterThanRowFalseCheckTest() {
		compare[0][0] = new FutoshikiSquare(1, 0, 0);
		compare[0][1] = new FutoshikiSquare(2, 0, 1);
		Constraint test = new MoreThan(compare[0][0], compare[0][1]);
		assertFalse(test.check(2));
	}
	
	/**
	 * Greater than column false check test.
	 */
	@Test
	public void greaterThanColumnFalseCheckTest() {
		compare[0][0] = new FutoshikiSquare(1, 0, 0);
		compare[1][0] = new FutoshikiSquare(2, 1, 0);
		Constraint test = new MoreThan(compare[0][0], compare[1][0]);
		assertFalse(test.check(2));
	}
	
	/**
	 * Constraint true check test.
	 */
	@Test
	public void constraintTrueCheckTest() {
		FutoshikiPuzzle constraintCompare = new FutoshikiPuzzle(2, true);
		constraintCompare.getGrid(0, 0).setValue(1);
		constraintCompare.getGrid(0, 1).setValue(2);
		constraintCompare.getGrid(1, 0).setValue(2);
		constraintCompare.getGrid(1, 1).setValue(1);
		constraintCompare.setLess("row", 0, 0);
		constraintCompare.setMore("row", 1, 0);
		constraintCompare.setLess("col", 0, 0);
		constraintCompare.setMore("col", 0, 1);
		
		assertTrue(constraintCompare.rowConCheck());
		assertTrue(constraintCompare.colConCheck());
	}
	
	/**
	 * Constrain false check test.
	 */
	@Test
	public void constrainFalseCheckTest() {
		FutoshikiPuzzle constraintCompare = new FutoshikiPuzzle(2, true);
		constraintCompare.getGrid(0, 0).setValue(1);
		constraintCompare.getGrid(0, 1).setValue(2);
		constraintCompare.getGrid(1, 0).setValue(2);
		constraintCompare.getGrid(1, 1).setValue(1);
		constraintCompare.setMore("row", 0, 0);
		constraintCompare.setLess("row", 1, 0);
		constraintCompare.setMore("col", 0, 0);
		constraintCompare.setLess("col", 0, 1);
		
		assertFalse(constraintCompare.rowConCheck());
		assertFalse(constraintCompare.colConCheck());
	}
	
	public void isLegalTest() {
		FutoshikiPuzzle constraintCompare = new FutoshikiPuzzle(2, true);
		constraintCompare.getGrid(0, 0).setValue(1);
		constraintCompare.getGrid(0, 1).setValue(0);
		constraintCompare.getGrid(1, 0).setValue(2);
		constraintCompare.getGrid(1, 1).setValue(0);
		constraintCompare.setLess("row", 0, 0);
		constraintCompare.setNull("row", 1, 0);
		constraintCompare.setNull("col", 0, 0);
		constraintCompare.setMore("col", 0, 1);
		
		assertTrue(constraintCompare.isLegal());
	}
}

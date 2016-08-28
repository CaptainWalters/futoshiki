/*
 * FutoshikiPuzzle.java
 * @author Oliver Walters
 * @version 1.1
 */
package futoshiki_puzzle;

import java.util.HashSet;
import java.util.Random;


/**
 * The Class FutoshikiPuzzle.
 */
public class FutoshikiPuzzle {

	/** The grid. */
	FutoshikiSquare[][] grid;
	
	/** The row constraints. */
	Constraint[][] rowConstraints;
	
	/** The column constraints. */
	Constraint[][] columnConstraints;
	
	private FutoshikiGUI ui;
	
	/** The problems. */
	private String problems = "";

	/**
	 * Instantiates a new Futoshiki puzzle.
	 *
	 * @param size the size
	 */
	public FutoshikiPuzzle(int size, boolean r){
		grid = new FutoshikiSquare[size][size];
		rowConstraints = new Constraint[size][size-1]; 
		columnConstraints = new Constraint[size-1][size];
		
		if(r) {
			fillPuzzle();
		}
		else {
			setPuzzle();
		}
	}

	/**
	 * Empty.
	 *
	 * @param isNumber the is number
	 * @return true, if successful
	 */
	public boolean empty(boolean isNumber) {
		Random rand = new Random();
		int isEmpty, size;
		
		if (isNumber) {
			size = grid.length;
			isEmpty = rand.nextInt(size)+1;
		}
		else{
			size = grid.length*2;
			isEmpty = rand.nextInt(size)+1;
		}
		return (isEmpty < size);
		
	}
	
	/**
	 * Sets the square.
	 *
	 * @param i the i
	 * @param j the j
	 * @return the futoshiki square
	 */
	public FutoshikiSquare setSquare(int number, int i, int j){
		return new FutoshikiSquare(number, i, j);
	}
	
	public int randSquare() {
		Random rand = new Random();
		int number = rand.nextInt(grid.length)+1;
		
		if(!empty(true)) { 
			return number;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Constraint chance.
	 *
	 * @return the int
	 */
	public int conChance() {
		Random rand = new Random();
		int choose = rand.nextInt(2)+1;
		
		if(!empty(false)) {
			return choose;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Sets the row constraint.
	 *
	 * @param i the i
	 * @param j the j
	 * @return the constraint
	 */
	public Constraint setRowConstraint(int i, int j){
		int choice = conChance();
		
		switch(choice){
		case 1:
			return setLess(i, j, true);
		case 2:
			return setMore(i, j, true);
		default:
			return null;
		}
	}
	
	/**
	 * Sets the column constraint.
	 *
	 * @param i the i
	 * @param j the j
	 * @return the constraint
	 */
	public Constraint setColumnConstraint(int i, int j){
		int choice = conChance();
		
		switch(choice){
		case 1:
			return setLess(i, j, false);
		case 2:
			return setMore(i, j, false);
		default:
			return null;
		}
	}
	
	public Constraint setLess(int x, int y, boolean row) {
		if(row) {
			return new LessThan(grid[x][y], grid[x][y+1]);
		}
		else {
			return new LessThan(grid[x][y], grid[x+1][y]);
		}
	}
	
	public Constraint setMore(int x, int y, boolean row) {
		if(row) {
			return new MoreThan(grid[x][y], grid[x][y+1]);
		}
		else {
			return new MoreThan(grid[x][y], grid[x+1][y]);
		}
	}
	
	/**
	 * Fill puzzle.
	 */
	public void fillPuzzle(){
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid.length;j++) {
				grid[i][j] = setSquare(randSquare(), i, j);
			}
		}
		for(int i=0;i<rowConstraints.length;i++) {
			for(int j=0;j<rowConstraints[0].length;j++) {
				rowConstraints[i][j] = setRowConstraint(i, j);
			}
		}
		for(int i=0;i<columnConstraints.length;i++) {
			for(int j=0;j<columnConstraints[0].length;j++) {
				columnConstraints[i][j] = setColumnConstraint(i, j);
			}
		}
	}
	
	public void setPuzzle() {
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid.length;j++) {
				grid[i][j] = setSquare(0, i, j);
			}
		}
		for(int i=0;i<rowConstraints.length;i++) {
			for(int j=0;j<rowConstraints[0].length;j++) {
				rowConstraints[i][j] = null;
			}
		}
		for(int i=0;i<columnConstraints.length;i++) {
			for(int j=0;j<columnConstraints[0].length;j++) {
				columnConstraints[i][j] = null;
			}
		}
		grid[0][0] = setSquare(4, 0, 0);
		grid[3][0] = setSquare(2, 3, 0);
		grid[4][4] = setSquare(2, 4, 4);
		rowConstraints[1][3] = setLess(1, 3, true);
		rowConstraints[2][1] = setMore(2, 1, true);
		rowConstraints[3][0] = setMore(3, 0, true);
		rowConstraints[4][2] = setMore(4, 2, true);
		columnConstraints[0][0] = setLess(0, 0, false);
		columnConstraints[1][0] = setMore(1, 0, false);
		columnConstraints[1][1] = setMore(1, 1, false);
		columnConstraints[3][3] = setLess(3, 3, false);
		
	}
	
	/* 
	 * Converts puzzle to a string.
	 */
	@Override
	public String toString(){
		String topBot = "---";
		String line = "";
		String puz = "";
		
		for(int i=1;i<grid.length;i++) {
			topBot += " ---";
		}
		topBot += "\r\n";
		
		for(int i=0;i<grid.length;i++) {
			line = "";
			for(int j=0;j<grid.length;j++){
				if(grid[i][j].getValue() > 0) {
					line += "|" + grid[i][j].getValue() + "|";
				}
				else {
					line += "| |";
				}
				if(j<rowConstraints[i].length && rowConstraints[i][j]!=null) {
					line += rowConstraints[i][j].getSymbol();
				}
				else {
					line += " ";
				}
			}
			line += "\r\n";
			puz += topBot + line + topBot;
			if(i<columnConstraints.length) {
				line = "";
				for(int j=0;j<columnConstraints[i].length;j++) {
					if(columnConstraints[i][j]!=null) {
						line += " " + columnConstraints[i][j].getSymbol() + " ";
					}
					else{
						line += "   ";
					}
					if(j<grid.length-1) {
						line += " ";
					}
				}
				puz += line + "\r\n";
			}
		}
		return puz;
	}

	/**
	 * Row check.
	 *
	 * @return true, if successful
	 */
	protected boolean rowCheck(){
		boolean test = true;
		HashSet<Integer> chk = new HashSet<>(grid.length);
		HashSet<Integer> duplicates = new HashSet<>(grid.length);
		
		for(int i=0;i<grid.length;i++){
			chk.clear();
			for(int j=0;j<grid.length;j++){
				if(grid[i][j].getValue()>0){
					if(!chk.contains(grid[i][j].getValue())){
						chk.add(grid[i][j].getValue());
					}
					else {
						duplicates.add(i+1);
						test = false;
					}
				}
			}
		}
		if(!test) {
			problems += "There are duplicates in rows: " + duplicates + "\r\n";
		}
		return test;
	}

	/**
	 * Column check.
	 *
	 * @return true, if successful
	 */
	protected boolean columnCheck(){
		boolean test = true;
		HashSet<Integer> chk = new HashSet<>(grid.length);
		HashSet<Integer> duplicates = new HashSet<>(grid.length);
		
		for(int i=0;i<grid.length;i++){
			chk.clear();
			for(int j=0;j<grid.length;j++){
				if(grid[j][i].getValue()>0){
					if(!chk.contains(grid[j][i].getValue())){
						chk.add(grid[j][i].getValue());
					}
					else {
						duplicates.add(j+1);
						test = false;
					}
				}
			}
		}
		if(!test) {
			problems += "There are duplicates in columns: " + duplicates + "\r\n";
		}
		return test;
	}
	
	/**
	 * Row constraint check.
	 *
	 * @return true, if successful
	 */
	protected boolean rowConCheck(){
		boolean test = true;
		
		for(int i=0;i<rowConstraints.length;i++){
			for(int j=0;j<rowConstraints[i].length;j++){
				if(rowConstraints[i][j]!=null){
					if(!rowConstraints[i][j].check(grid.length)){
						problems += "The constraint in row " + (i+1) + " between boxes " + (j+1) + " and " + (j+2) + "is incorrect\r\n";
						test = false;
					}
				}
			}
		}
		return test;
	}
	
	/**
	 * Column constraint check.
	 *
	 * @return true, if successful
	 */
	protected boolean colConCheck(){
		boolean test = true;
		
		for(int i=0;i<columnConstraints.length;i++){
			for(int j=0;j<columnConstraints[i].length;j++){
				if(columnConstraints[i][j]!=null){
					if(!columnConstraints[i][j].check(grid.length)){
						problems += "The constraint in column " + (j+1) + " between boxes " + (i+1) + " and " + (i+2) + " is incorrect\r\n";
						test = false;
					}
				}
			}
		}
		return test;
	}
	
	/**
	 * Gets the puzzle size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return grid.length;
	}
	
    public void setGUI(FutoshikiGUI ui) {
    	this.ui = ui;
    }
	
	//Start of test methods	
	/**
	 * Gets the grid.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the grid
	 */
	protected FutoshikiSquare getGrid(int x, int y) {
		return grid[x][y];
	}
	
	/**
	 * Gets the constraint.
	 *
	 * @param type the type
	 * @param x the x
	 * @param y the y
	 * @return the constraint
	 */
	protected Constraint getConstraint(String type, int x, int y) {
		switch(type){
			case "row":
				return rowConstraints[x][y];
			case "col":
				return columnConstraints[x][y];
			default:
				return null;
		}
	}
	
	/**
	 * Sets the less.
	 *
	 * @param type the type
	 * @param x the x
	 * @param y the y
	 */
	protected void setLess(String type, int x, int y) {
		switch(type){
		case "row":
			rowConstraints[x][y] = new LessThan(grid[x][y], grid[x][y+1]);
			break;
		case "col":
			columnConstraints[x][y] = new LessThan(grid[x][y], grid[x+1][y]);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Sets the more.
	 *
	 * @param type the type
	 * @param x the x
	 * @param y the y
	 */
	protected void setMore(String type, int x, int y) {
		switch(type){
		case "row":
			rowConstraints[x][y] = new MoreThan(grid[x][y], grid[x][y+1]);
			break;
		case "col":
			columnConstraints[x][y] = new MoreThan(grid[x][y], grid[x+1][y]);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Sets the null.
	 *
	 * @param type the type
	 * @param x the x
	 * @param y the y
	 */
	protected void setNull(String type, int x, int y) {
		switch(type){
		case "row":
			rowConstraints[x][y] = null;
			break;
		case "col":
			columnConstraints[x][y] = null;
			break;
		default:
			break;
		}
	}
	//end of test methods
	
	/**
	 * Checks if is legal.
	 *
	 * @return true, if is legal
	 */
	public boolean isLegal(){
		boolean test1, test2, test3, test4;
		boolean finalTest = true;

		test1 = rowCheck();
		test2 = columnCheck();
		test3 = rowConCheck();
		test4 = colConCheck();
		
		if (!test1 || !test2 || !test3 || !test4) {
			finalTest = false;
			//System.out.println("FAIL\r\n");
			//System.out.println(getProblems());
		}
		return finalTest;
	}
	
	/**
	 * Gets the problems.
	 *
	 * @param val the value
	 * @param type the type
	 * @return the problems
	 */
	public String getProblems(){
		return problems;
	}

	public boolean solve() {
		FutoshikiSquare f;
		int x, y;
		
		if (!isLegal()) {
			return false;
		}
		
		f = nextEmpty();
		if(f == null) {
			return true;
		}
		else{
			x=f.getx();
			y=f.gety();
		}
		for(int n=1;n<=5;n++) {
			f.setValue(n);
			ui.gridBoxes[x][y].setText(Integer.toString(f.getValue()));
			if(solve()) {
				return true;
			}
		}
		f.setValue(0);
		return false;
	}
	
	public FutoshikiSquare nextEmpty() {
		
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid.length;j++) {
				if(grid[i][j].getValue() == 0) {
					return grid[i][j];
				}
			}
		}
		return null;
	}
	
	
}

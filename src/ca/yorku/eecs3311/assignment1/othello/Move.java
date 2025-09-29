package ca.yorku.eecs3311.assignment1.othello;

// TODO: Javadoc this class

/**
 * Represents a move on the Othello board with a specific row and column
 * coordinate This class provides getter methods to retrieve the row and column
 * A toString method of the move is also provided
 */
public class Move {
	private int row, col;
	
	/**
	 * Creates a new move with the specified row and column
	 * 
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 */
	public Move(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Returns row position of move
	 * 
	 * @return row index of move
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns column position of move
	 * 
	 * @return column index of move
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @return a string representation of the move
	 */
	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}
}

package ca.yorku.eecs3311.assignment1.othello;

/**
 * Keep track of all of the tokens on the board. This understands some
 * interesting things about an Othello board, what the board looks like at the
 * start of the game, what the players tokens look like ('X' and 'O'), whether
 * given coordinates are on the board, whether either of the players have a move
 * somewhere on the board, what happens when a player makes a move at a specific
 * location (the opposite players tokens are flipped).
 * 
 * Othello makes use of the OthelloBoard.
 *
 */
public class OthelloBoard {
	
	public static final char EMPTY = ' ', P1 = 'X', P2 = 'O', BOTH = 'B';
	private int dim = 8;
	private char[][] board;

	public OthelloBoard(int dim) {
		this.dim = dim;
		board = new char[this.dim][this.dim];
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {
				this.board[row][col] = EMPTY;
			}
		}
		int mid = this.dim / 2;
		this.board[mid - 1][mid - 1] = this.board[mid][mid] = P1;
		this.board[mid][mid - 1] = this.board[mid - 1][mid] = P2;
	}

	public int getDimension() {
		return this.dim;
	}

	/**
	 * 
	 * @param row    of position player is trying to move to
	 * @param col    of position player is trying to move to
	 * @param player P1 or P2
	 * @return whether the player has a valid move
	 */
	public boolean isValidMove(int row, int col, char player) {
		// check if coordinates are valid and spot is empty
		if (!validCoordinate(row, col) || get(row, col) != EMPTY) {
			return false;
		}

		// check all directions around the spot for a valid move
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				if (drow == 0 && dcol == 0) {
					continue; // skip no-move direction
				}
				if (hasMove(row, col, drow, dcol) == player) {
					return true; // valid move found in this direction
				}
			}
		}
		// if no valid moves found return false
		return false;
	}

	/**
	 * 
	 * @param player either P1 or P2
	 * @return P2 or P1, the opposite of player
	 */
	public static char otherPlayer(char player) {
		if (player == P1) {
			return P2;
		} else if (player == P2) {
			return P1;
		} else {
			return EMPTY;
		}
	}

	/**
	 * 
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return P1,P2 or EMPTY, EMPTY is returned for an invalid (row,col)
	 */
	public char get(int row, int col) {
		if (!(validCoordinate(row, col))) { // perform valid check
			return EMPTY;
		}
		return board[row][col];
	}

	/**
	 * 
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return whether (row,col) is a position on the board. Example: (6,12) is not
	 *         a position on the board.
	 */
	private boolean validCoordinate(int row, int col) {
		boolean isRowValid = row >= 0 && row < dim; // check if row is in bounds
		boolean isColValid = col >= 0 && col < dim; // check if column is in bounds
		return isRowValid && isColValid;
	}

	/**
	 * Check if there is an alternation of P1 next to P2, starting at (row,col) in
	 * direction (drow,dcol). That is, starting at (row,col) and heading in
	 * direction (drow,dcol), you encounter a sequence of at least one P1 followed
	 * by a P2, or at least one P2 followed by a P1. The board is not modified by
	 * this method. Why is this method important? If
	 * alternation(row,col,drow,dcol)==P1, then placing P1 right before (row,col),
	 * assuming that square is EMPTY, is a valid move, resulting in a collection of
	 * P2 being flipped.
	 * 
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return P1, if there is an alternation P2 ...P2 P1, or P2 if there is an
	 *         alternation P1 ... P1 P2 in direction (dx,dy), EMPTY if there is no
	 *         alternation
	 */
	private char alternation(int row, int col, int drow, int dcol) {

		// ensure the position is empty
		if (get(row, col) != EMPTY) {
			return EMPTY;
		}

		// get coordinates from moving in direction
		int r = row + drow;
		int c = col + dcol;

		// check validity of new cords and ensure its not empty
		if (!(validCoordinate(r, c)) || get(r, c) == EMPTY) { // check
			return EMPTY;
		}

		// firstPlayer = the one we bump into immediately
		char firstPlayer = get(r, c);
		// keep moving in that direction while the first player owns it
		while (validCoordinate(r, c) && get(r, c) == firstPlayer) {
			r = r + drow;
			c = c + dcol;
		}

		// if we are out of bounds or empty return EMPTY
		if (!(validCoordinate(r, c)) || get(r, c) == EMPTY) {
			return EMPTY;
		}

		// if current position belongs to the otherPlayer then return the OTHER player
		// This means: if we have a sequence like O...O X, then X can flip the O's
		if (get(r, c) == otherPlayer(firstPlayer)) {
			return get(r, c);
		}

		// otherwise return EMPTY
		return EMPTY;
	}

	/**
	 * flip all other player tokens to player, starting at (row,col) in direction
	 * (drow, dcol). Example: If (drow,dcol)=(0,1) and player==O then XXXO will
	 * result in a flip to OOOO
	 * 
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow   the row direction, in {-1,0,1}
	 * @param dcol   the col direction, in {-1,0,1}
	 * @param player Either OthelloBoard.P1 or OthelloBoard.P2, the target token to
	 *               flip to.
	 * @return the number of other player tokens actually flipped, -1 if this is not
	 *         a valid move in this one direction, that is, EMPTY or the end of the
	 *         board is reached before seeing a player token.
	 */
	private int flip(int row, int col, int drow, int dcol, char player) {
		
		boolean valid = validCoordinate(row,col);
		int counter = 0;
		
		if(valid && get(row,col)==EMPTY) {
			if(hasMove(row,col,drow,dcol)==player) {
				//check next position in direction
				int currentRow = row + drow;
				int currentCol = col + dcol;
				
				// Keep flipping until we reach the player's piece or go out of bounds
				while(validCoordinate(currentRow, currentCol) && get(currentRow,currentCol) != player) {
					
					board[currentRow][currentCol] = player;
					counter++;
					
					currentRow += drow;
					currentCol +=dcol;
				}
			}
		    return counter;

		}
			return -1;
	}

	/**
	 * Return which player has a move (row,col) in direction (drow,dcol).
	 * 
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return P1,P2,EMPTY
	 */
	private char hasMove(int row, int col, int drow, int dcol) {
		
		boolean valid = validCoordinate(row,col);
		
		if(!valid || get(row,col)!=EMPTY){
	
			return EMPTY;
		}
		return alternation(row,col,drow,dcol);
		
	}

	/**
	 * 
	 * @return whether P1,P2 or BOTH have a move somewhere on the board, EMPTY if
	 *         neither do.
	 */
	public char hasMove() {
		
		boolean p1HasMove = false;
		boolean p2HasMove = false;
		
		for(int row=0;row<dim;row++) {
			for(int col=0;col<dim;col++) {
				if(get(row,col)==EMPTY) {
					for(int drow=-1;drow<=1;drow++) {
						for(int dcol=-1;dcol<=1;dcol++) {
							
							//if no position changed, continue the scanning
							if(drow==0 && dcol==0) {
								continue;
							}
							if(hasMove(row,col,drow,dcol)==EMPTY) {
								continue;
							}
							else {
								//
								char whoWins = hasMove(row,col,drow,dcol);
								if(whoWins == P1) {
									 p1HasMove = true;
								}
								else
									p2HasMove = true;
							}
							
							
						}
					}
				}
			}
		}
		if(p1HasMove && p2HasMove)
			return BOTH;
		else if(p1HasMove) {
			return P1;
		}
		else if(p2HasMove) {
			return P2;
		}
		return EMPTY;
	}

	/**
	 * Make a move for player at position (row,col) according to Othello rules,
	 * making appropriate modifications to the board. Nothing is changed if this is
	 * not a valid move.
	 * 
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param player P1 or P2
	 * @return true if player moved successfully at (row,col), false otherwise
	 */
	public boolean move(int row, int col, char player) {
		// HINT: Use some of the above helper methods to get this methods
		// job done!!

		boolean hasFoundFlag = false;
		if(validCoordinate(row,col)&& get(row,col)==EMPTY) {
			for(int drow=-1;drow<=1;drow++) {
				for(int dcol = -1; dcol<=1;dcol++) {
					
					if(drow==0&&dcol==0) {
						continue;
					}
					// Check if this direction allows the player to make a move
					if(hasMove(row,col,drow,dcol)==player) {
						hasFoundFlag = true;
						flip(row,col,drow,dcol,player);
					}
					
				}
			}
			if(hasFoundFlag) {
				board[row][col]=player;
			}
		}
		return hasFoundFlag;
	}

	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens on the board for player
	 */
	public int getCount(char player) {
		int count = 0;

		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				if (board[row][col] == player) {
					count++;
				}
			}
		}

		return count;
	}


	/**
	 * @return a string representation of this, just the play area, with no
	 *         additional information. DO NOT MODIFY THIS!!
	 */
	public String toString() {
		/**
		 * See assignment web page for sample output.
		 */
		String s = "";
		s += "  ";
		for (int col = 0; col < this.dim; col++) {
			s += col + " ";
		}
		s += '\n';

		s += " +";
		for (int col = 0; col < this.dim; col++) {
			s += "-+";
		}
		s += '\n';

		for (int row = 0; row < this.dim; row++) {
			s += row + "|";
			for (int col = 0; col < this.dim; col++) {
				s += this.board[row][col] + "|";
			}
			s += row + "\n";

			s += " +";
			for (int col = 0; col < this.dim; col++) {
				s += "-+";
			}
			s += '\n';
		}
		s += "  ";
		for (int col = 0; col < this.dim; col++) {
			s += col + " ";
		}
		s += '\n';
		return s;
	}

	/**
	 * A quick test of OthelloBoard. Output is on assignment page.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		OthelloBoard ob = new OthelloBoard(8);
		System.out.println(ob.toString());
		System.out.println("getCount(P1)=" + ob.getCount(P1));
		System.out.println("getCount(P2)=" + ob.getCount(P2));
		for (int row = 0; row < ob.dim; row++) {
			for (int col = 0; col < ob.dim; col++) {
				ob.board[row][col] = P1;
			}
		}
		System.out.println(ob.toString());
		System.out.println("getCount(P1)=" + ob.getCount(P1));
		System.out.println("getCount(P2)=" + ob.getCount(P2));

		// Should all be blank
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
			}
		}

		for (int row = 0; row < ob.dim; row++) {
			for (int col = 0; col < ob.dim; col++) {
				if (row == 0 || col == 0) {
					ob.board[row][col] = P2;
				}
			}
		}
		System.out.println(ob.toString());

		// Should all be P2 (O) except drow=0,dcol=0
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("direction=(" + drow + "," + dcol + ")");
				System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
			}
		}

		// Can't move to (4,4) since the square is not empty
		System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));

		ob.board[4][4] = EMPTY;
		ob.board[2][4] = EMPTY;

		System.out.println(ob.toString());

		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("direction=(" + drow + "," + dcol + ")");
				System.out.println("hasMove at (4,4) in above direction =" + ob.hasMove(4, 4, drow, dcol));
			}
		}
		System.out.println("who has a move=" + ob.hasMove());
		System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));
		System.out.println(ob.toString());

	}
}

package ca.yorku.eecs3311.assignment1.othello;

/**
 * PlayerGreedy makes a move by considering all possible moves that the player
 * can make. Each move leaves the player with a total number of tokens.
 * getMove() returns the first move which maximizes the number of
 * tokens owned by this player. In case of a tie, between two moves,
 * (row1,column1) and (row2,column2) the one with the smallest row wins. In case
 * both moves have the same row, then the smaller column wins.
 * 
 * Example: Say moves (2,7) and (3,1) result in the maximum number of tokens for
 * this player. Then (2,7) is returned since 2 is the smaller row.
 * 
 * Example: Say moves (2,7) and (2,4) result in the maximum number of tokens for
 * this player. Then (2,4) is returned, since the rows are tied, but (2,4) has
 * the smaller column.
 * 
 * See the examples supplied in the assignment handout.
 * 
 *
 */

public class PlayerGreedy {
	
	private Othello othello;
	private char player;
	
	
	public PlayerGreedy(Othello othello, char player) {
		
		this.othello = othello;
		this.player = player;
	}


	public Move getMove() {

		int best_counter = 0;
		Move bestmove = null;
		for(int row=0; row<othello.DIMENSION;row++) {
			for(int col = 0; col<othello.DIMENSION;col++) {
				if(othello.getBoard().isValidMove(row, col, player)) {

					OthelloBoard ob = othello.getBoard().copy();
					ob.move(row, col, player);
					
					int validToken = ob.getCount(player);
					
					if(validToken>best_counter) {
						best_counter = validToken;
						bestmove = new Move(row,col);

					}
				}
				
			}

		}
		return bestmove;
	}
}

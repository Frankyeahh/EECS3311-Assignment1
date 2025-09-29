package ca.yorku.eecs3311.assignment1.othello;

import java.util.ArrayList;
import java.util.Random;


/**
 * PlayerRandom makes a move by first determining all possible moves that this
 * player can make, putting them in an ArrayList, and then randomly choosing one
 * of them.
 *
 */
public class PlayerRandom {
	
	private Random rand = new Random();

	public Move getMove(OthelloBoard board, char player) {
		ArrayList<Move> possibleMoves = new ArrayList<>();
		
		// search for possible moves
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				if (board.isValidMove(row, col, player)) {
					possibleMoves.add(new Move(row, col));
				}
			}
		}
 
		// if no possible moves found return null
		if (possibleMoves.isEmpty()) {
			return null;
		}
		
		// randomly select a move from possibleMoves list
		int random = rand.nextInt(possibleMoves.size());
		return possibleMoves.get(random);
	}
}

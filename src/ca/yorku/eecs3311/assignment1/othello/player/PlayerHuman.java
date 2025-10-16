package ca.yorku.eecs3311.assignment1.othello.player;

import ca.yorku.eecs3311.assignment1.othello.game.Move;
import ca.yorku.eecs3311.assignment1.othello.game.Othello;
import ca.yorku.eecs3311.assignment1.othello.game.OthelloBoard;
import ca.yorku.eecs3311.assignment1.exceptions.InvalidMoveException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * PlayerHuman handles input from a human player via the console.
 * Prompts the user to enter row and column coordinates for their move.
 * Validates the moves and the user needs to try again if it was an invalid move
 *
 */
public class PlayerHuman implements Player{
	
	private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter 1-8";
	private static final String IO_ERROR_MESSAGE = "I/O Error";
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	private Othello othello;
	private char player;

	public PlayerHuman(Othello othello, char player) {
		
		this.othello = othello;
		this.player = player;
	}

	public Move getMove() {
		// check if current player has any valid moves first
		char whoCanMove = othello.getBoard().hasMove();
	    	if (whoCanMove != player && whoCanMove != OthelloBoard.BOTH) {
	    		return null; // Current player can't move, skip turn
	     }
		
		while(true) {
			try {
				int row = getMove("row: ");
				int col = getMove("col: ");
				
				// validate if this is valid move
				if(!othello.getBoard().isValidMove(row, col, player)) {
					throw new InvalidMoveException(
							"Invalid move at (" + row + "," + col + "). That position doesn't flip any opponent pieces. Please try again."
						);
				}
				return new Move(row, col);
			
		} catch (InvalidMoveException e) {
			System.out.println(e.getMessage());
		}
		
	}
	}

	private int getMove(String message) {
		
		int move, lower = 0, upper = 7;
		while (true) {
			try {
				System.out.print(message);
				String line = PlayerHuman.stdin.readLine();
				move = Integer.parseInt(line);
				if (lower <= move && move <= upper) {
					return move;
				} else {
					System.out.println(INVALID_INPUT_MESSAGE);
				}
			} catch (IOException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
				break;
			} catch (NumberFormatException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
			}
		}
		return -1;
	}
}

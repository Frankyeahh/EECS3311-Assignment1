package ca.yorku.eecs3311.assignment1.othello.controller;

import ca.yorku.eecs3311.assignment1.othello.game.Othello;
import ca.yorku.eecs3311.assignment1.othello.game.OthelloBoard;
import ca.yorku.eecs3311.assignment1.othello.game.OthelloPlay;
import ca.yorku.eecs3311.assignment1.othello.player.PlayerGreedy;
import ca.yorku.eecs3311.assignment1.othello.player.PlayerHuman;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a greedy strategy. 
 *
 */
public class OthelloControllerHumanVSGreedy {
	
	protected Othello othello;
	PlayerGreedy player2;
	PlayerHuman player1;

	
	public OthelloControllerHumanVSGreedy() {
		this.othello = new Othello();
		this.player1 = new PlayerHuman(othello, OthelloBoard.P1);
		this.player2 = new PlayerGreedy(othello,OthelloBoard.P2);
	}

	/**
	 * Run main to play a Human (P1) against the computer P2. 
	 * The computer uses a greedy strategy, that is, it picks the first
	 * move which maximizes its number of token on the board.
	 * The output should be almost identical to that of OthelloControllerHumanVSHuman.
	 * @param args
	 */
	public static void main(String[] args) {
        OthelloControllerHumanVSGreedy oc = new OthelloControllerHumanVSGreedy();
        OthelloPlay gamePlayer = new OthelloPlay(oc.othello, oc.player1, oc.player2, false);
        gamePlayer.play();
	}
}

package ca.yorku.eecs3311.assignment1.othello.controller;

import ca.yorku.eecs3311.assignment1.othello.game.Othello;
import ca.yorku.eecs3311.assignment1.othello.game.OthelloBoard;
import ca.yorku.eecs3311.assignment1.othello.game.OthelloPlay;
import ca.yorku.eecs3311.assignment1.othello.player.PlayerHuman;
import ca.yorku.eecs3311.assignment1.othello.player.PlayerRandom;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a random strategy. 
 * 
 */
public class OthelloControllerHumanVSRandom {
	
	protected Othello othello;
	PlayerHuman player1;
	PlayerRandom player2;

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with a user and a computer
	 */
	public OthelloControllerHumanVSRandom() {
		this.othello = new Othello();
		this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerRandom(this.othello, OthelloBoard.P2);
	}

	/**
	 * Run main to play a Human (P1) against the computer P2. 
	 * The computer uses a random strategy, that is, it randomly picks 
	 * one of its possible moves.
	 * The output should be almost identical to that of OthelloControllerHumanVSHuman.

	 * @param args
	 */
	public static void main(String[] args) {
		OthelloControllerHumanVSRandom oc = new OthelloControllerHumanVSRandom();
        OthelloPlay gamePlayer = new OthelloPlay(oc.othello, oc.player1, oc.player2, false);
        gamePlayer.play();
	}
}

package ca.yorku.eecs3311.assignment1.othello.controller;

import ca.yorku.eecs3311.assignment1.othello.game.Othello;
import ca.yorku.eecs3311.assignment1.othello.game.OthelloBoard;
import ca.yorku.eecs3311.assignment1.othello.game.OthelloPlay;
import ca.yorku.eecs3311.assignment1.othello.player.PlayerHuman;

/**
 * Run the main from this class to play two humans against eachother. Only
 * minimal modifications to this class are permitted.
 *
 */
public class OthelloControllerHumanVSHuman {

	Othello othello;
	PlayerHuman player1, player2;

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with two users at the console.
	 */
	public OthelloControllerHumanVSHuman() {
		this.othello = new Othello();
		this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerHuman(this.othello, OthelloBoard.P2);
	}

	/**
	 * Run main to play two Humans against each other at the console.
	 * @param args
	 */
	public static void main(String[] args) {
		OthelloControllerHumanVSHuman oc = new OthelloControllerHumanVSHuman();
        OthelloPlay gamePlayer = new OthelloPlay(oc.othello, oc.player1, oc.player2, false);
        gamePlayer.play();
	}

}

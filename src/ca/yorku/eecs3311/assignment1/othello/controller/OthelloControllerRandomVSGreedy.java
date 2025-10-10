package ca.yorku.eecs3311.assignment1.othello.controller;

import ca.yorku.eecs3311.assignment1.othello.game.Othello;
import ca.yorku.eecs3311.assignment1.othello.game.OthelloBoard;
import ca.yorku.eecs3311.assignment1.othello.game.OthelloPlay;
import ca.yorku.eecs3311.assignment1.othello.player.PlayerGreedy;
import ca.yorku.eecs3311.assignment1.othello.player.PlayerRandom;

/**
 * The goal here is to print out the probability that Random wins and Greedy
 * wins as a result of playing 10000 games against each other with P1=Random and
 * P2=Greedy. What is your conclusion, which is the better strategy?
 *
 */
public class OthelloControllerRandomVSGreedy {

	/**
	 * Run main to execute the simulation and print out the two line results.
	 * Output looks like: 
	 * Probability P1 wins=.75 
	 * Probability P2 wins=.20
	 * @param args
	 */
	public static void main(String[] args) {
		
		int p1wins = 0, p2wins = 0, numGames = 10000;

        // loop over games and simulate
        for(int game = 0; game < numGames; game++) {
            Othello othello = new Othello();
            PlayerRandom player1 = new PlayerRandom(othello, OthelloBoard.P1);
            PlayerGreedy player2 = new PlayerGreedy(othello, OthelloBoard.P2);
            OthelloPlay gamePlayer = new OthelloPlay(othello, player1, player2, true);
            gamePlayer.play();

            if(othello.getWinner() == OthelloBoard.P1)
                p1wins++;
            else if (othello.getWinner() == OthelloBoard.P2)
                p2wins++;

        }

		System.out.println("Probability P1 wins=" + (float) p1wins / numGames);
		System.out.println("Probability P2 wins=" + (float) p2wins / numGames);

	}
}

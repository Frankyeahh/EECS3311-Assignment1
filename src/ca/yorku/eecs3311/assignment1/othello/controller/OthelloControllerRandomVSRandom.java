package ca.yorku.eecs3311.assignment1.othello.controller;

import ca.yorku.eecs3311.assignment1.othello.game.Othello;
import ca.yorku.eecs3311.assignment1.othello.game.OthelloBoard;
import ca.yorku.eecs3311.assignment1.othello.game.OthelloPlay;
import ca.yorku.eecs3311.assignment1.othello.player.PlayerRandom;

/**
 * Determine whether the first player or second player has the advantage when
 * both are playing a Random Strategy.
 * 
 * Do this by creating two players which use a random strategy and have them
 * play each other for 10000 games. What is your conclusion, does the first or
 * second player have some advantage, at least for a random strategy? 
 * State the null hypothesis H0, the alternate hypothesis Ha and 
 * about which your experimental results support. Place your short report in
 * randomVsRandomReport.txt.
 * 
 *
 */
public class OthelloControllerRandomVSRandom {
	/**
	 * Run main to execute the simulation and print out the two line results.
	 * Output looks like 
	 * Probability P1 wins=.75 
	 * Probability P2 wins=.20
     */

	public static void main(String[] args) {
		
		int p1wins = 0, p2wins = 0, numGames = 10000;

        // loop over games and simulate
        for(int game = 0; game < numGames; game++) {
            Othello othello = new Othello();
            PlayerRandom player1 = new PlayerRandom(othello, OthelloBoard.P1);
            PlayerRandom player2 = new PlayerRandom(othello, OthelloBoard.P2);
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

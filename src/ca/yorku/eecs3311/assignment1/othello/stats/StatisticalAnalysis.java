package ca.yorku.eecs3311.assignment1.othello.stats;

import ca.yorku.eecs3311.assignment1.othello.game.Othello;
import ca.yorku.eecs3311.assignment1.othello.game.OthelloBoard;
import ca.yorku.eecs3311.assignment1.othello.game.OthelloPlay;
import ca.yorku.eecs3311.assignment1.othello.player.PlayerRandom;

/**
 * Statistical analysis for RandomVsRandom simulation
 * Implements the "hacker statistics" approach from Jake Vanderplas
 */
public class StatisticalAnalysis {

    public static void main(String[] args) {
        int p1Wins = 0;
        int p2Wins = 0;

        // run simulation of randomVsrandom
        for(int game = 0; game < 10000; game++){
            Othello othello = new Othello();
            PlayerRandom player1 = new PlayerRandom(othello, OthelloBoard.P1);
            PlayerRandom player2 = new PlayerRandom(othello, OthelloBoard.P2);
            OthelloPlay gamePlayer = new OthelloPlay(othello, player1, player2, true);
            gamePlayer.play();
            if(othello.getWinner() == OthelloBoard.P1)
                p1Wins++;
            else if (othello.getWinner() == OthelloBoard.P2)
                p2Wins++;
        }

        int totalDecidedGames = p1Wins + p2Wins;
        int observedDifference = Math.abs(p1Wins - p2Wins);

        System.out.println("Observed: P1=" + p1Wins + " P2=" + p2Wins + " Diff=" + observedDifference);

        // simulate 100,000 fair games
        int extremeCount = 0;
        for (int i = 0; i < 100000; i++) {
            int simP1 = 0;
            for (int j = 0; j < totalDecidedGames; j++) {
                if (Math.random() < 0.5) simP1++;
            }
            if (Math.abs(simP1 - (totalDecidedGames - simP1)) >= observedDifference) {
                extremeCount++;
            }
        }

            double pValue = extremeCount / 100000.0;
            System.out.println("P-value: " + pValue);
            if (pValue < 0.05) {
                String winner = p1Wins > p2Wins ? "P1" : "P2";
                System.out.println("Reject the H0 - " + winner + " has significant advantage");
            } else {
                System.out.println("Fail to reject H0 - No significant advantage");
            }
            }
}

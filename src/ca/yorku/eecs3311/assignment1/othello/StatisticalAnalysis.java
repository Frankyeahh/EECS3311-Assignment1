package ca.yorku.eecs3311.assignment1.othello;
/**
 * Statistical analysis for RandomVsRandom simulation
 * Implements the "hacker statistics" approach from Jake Vanderplas' PyCon talk
 */
public class StatisticalAnalysis {

    public static void main(String[] args) {
        int p1Wins = 4502;
        int p2Wins = 5074;
        // exclude tied games from data
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
                System.out.println("REJECT H0 - " + winner + " has significant advantage");
            } else {
                System.out.println("FAIL TO REJECT H0 - No significant advantage");
            }
            }
}

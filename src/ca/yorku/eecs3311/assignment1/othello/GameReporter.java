package ca.yorku.eecs3311.assignment1.othello;

public class GameReporter {
	private Othello othello;

	public GameReporter(Othello othello) {
		this.othello = othello;
	}

	public void reportMove(char whosTurn, Move move) {
		System.out.println(whosTurn + " makes move " + move + "\n");
	}

	public void report() {
		String s = othello.getBoardString() + OthelloBoard.P1 + ":" + othello.getCount(OthelloBoard.P1) + " "
				+ OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2) + "  " + othello.getWhosTurn()
				+ " moves next";
		System.out.println(s);
	}

	public void reportFinal() {
		String s = othello.getBoardString() + OthelloBoard.P1 + ":" + othello.getCount(OthelloBoard.P1) + " "
				+ OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2) + "  " + othello.getWinner() + " won\n";
		System.out.println(s);
	}

}

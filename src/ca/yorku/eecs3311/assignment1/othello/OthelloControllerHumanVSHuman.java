package ca.yorku.eecs3311.assignment1.othello;

/**
 * Run the main from this class to play two humans against eachother. Only
 * minimal modifications to this class are permitted.
 *
 */
public class OthelloControllerHumanVSHuman {

	protected Othello othello;
	PlayerHuman player1, player2;
	GameReporter reporter;

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with two users at the console.
	 */
	public OthelloControllerHumanVSHuman() {
		
		this.othello = new Othello();
		this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerHuman(this.othello, OthelloBoard.P2);
		this.reporter = new GameReporter(this.othello);
	}

	public void play() {
		
		while (!othello.isGameOver()) {
			this.reporter.report();

			Move move = null;
			char whosTurn = othello.getWhosTurn();

			if (whosTurn == OthelloBoard.P1)
				move = player1.getMove();
			if (whosTurn == OthelloBoard.P2)
				move = player2.getMove();

			this.reporter.reportMove(whosTurn, move);
			othello.move(move.getRow(), move.getCol());
		}
		this.reporter.reportFinal();
	}


	/**
	 * Run main to play two Humans against each other at the console.
	 * @param args
	 */
	public static void main(String[] args) {
		
		OthelloControllerHumanVSHuman oc = new OthelloControllerHumanVSHuman();
		oc.play();
	}

}

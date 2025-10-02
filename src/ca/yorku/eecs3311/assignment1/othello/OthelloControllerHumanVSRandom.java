package ca.yorku.eecs3311.assignment1.othello;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a random strategy. 
 * 
 */
public class OthelloControllerHumanVSRandom {
	
	protected Othello othello;
	PlayerHuman player1;
	PlayerRandom player2;
	GameReporter reporter;

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with a user and a computer
	 */
	public OthelloControllerHumanVSRandom() {

		this.othello = new Othello();
		this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerRandom(this.othello, OthelloBoard.P2);
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
			this.reporter.report(); // ← FIXED: Show board AFTER move is made
		}
		this.reporter.reportFinal();
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
		oc.play(); // this should work
	}
}

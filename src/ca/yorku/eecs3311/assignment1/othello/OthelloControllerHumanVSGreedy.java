package ca.yorku.eecs3311.assignment1.othello;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a greedy strategy. 
 *
 */
public class OthelloControllerHumanVSGreedy {
	
	protected Othello othello;
	PlayerGreedy P2;
	PlayerHuman P1;
	GameReporter gameReporter;
	
	
	public OthelloControllerHumanVSGreedy() {
		this.othello = new Othello();
		this.P1 = new PlayerHuman(othello,OthelloBoard.P1);
		this.P2 = new PlayerGreedy(othello,OthelloBoard.P2);
		this.gameReporter = new GameReporter(othello);
	}
	
public void play() {
	
	while(!othello.isGameOver()) {
		
		gameReporter.report();
		Move move = null;
		char whosTurn = othello.getWhosTurn();
		
		if(whosTurn == OthelloBoard.P1) {
			move = P1.getMove();
		}
		if(whosTurn == OthelloBoard.P2) {
			move = P2.getMove();
		}
		gameReporter.reportMove(whosTurn, move);
		
		othello.move(move.getRow(), move.getCol());
		
	}
	gameReporter.reportFinal();
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
		oc.play(); // this should work
	}
}

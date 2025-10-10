package ca.yorku.eecs3311.assignment1.othello.game;

import ca.yorku.eecs3311.assignment1.othello.player.Player;

public class OthelloPlay {

    Othello othello;
    Player player1, player2;
    GameReporter reporter;
    Boolean isBot;

    public OthelloPlay(Othello othello, Player player1, Player player2, Boolean isBot) {
        this.othello = othello;
        this.reporter = new GameReporter(this.othello);
        this.player1 = player1;
        this.player2 = player2;
        // if it is a bot vs. bot
        this.isBot = isBot;
    }


    public void play() {
        while (!othello.isGameOver()) {
            if(!isBot) reporter.report();

            Move move = null;
            char whosTurn = othello.getWhosTurn();

            if (whosTurn == OthelloBoard.P1)
                move = player1.getMove();
            if (whosTurn == OthelloBoard.P2)
                move = player2.getMove();

            if (move != null) {
                othello.move(move.getRow(), move.getCol());
            }
            else{
                othello.move(-1, -1);
            }

            if (!isBot) reporter.reportMove(whosTurn, move);
            othello.move(move.getRow(), move.getCol());
        }
        if(!isBot) reporter.reportFinal();
    }

}




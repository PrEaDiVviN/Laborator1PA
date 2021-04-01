package com.company;


public class Game implements Playable {

    public volatile Player firstToStart;
    private GameBoard gameBoard;
    private Player player1;
    private Player player2;

    public Game(GameBoard gameBoard, Player player1, Player player2, Player firstToStart) {
        this.gameBoard = gameBoard;
        this.player1 = player1;
        this.player2 = player2;
        this.firstToStart = firstToStart;
    }

    public void showResults() {
        player1.printBiggestList();
        player2.printBiggestList();
    }

    @Override
    public void play() {
        Runnable player1Playing = new PlayGame(this.player1, this.gameBoard, this);
        Runnable player2Playing = new PlayGame(this.player2, this.gameBoard, this);
        new Thread(player1Playing).start();
        new Thread(player2Playing).start();
        while (this.gameBoard.getRepresentationBoard().size() > 0) ;
    }
}

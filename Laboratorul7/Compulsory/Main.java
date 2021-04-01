package com.company;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Alex", 8.1f, Color.RED);
        Player player2 = new Player("Alin", 8.77f, Color.GREEN);
        GameBoard gameBoard = new GameBoard();
        gameBoard.generateGameBoard(15);
        Game game = new Game(gameBoard, player1, player2, player1);
        game.play();
        game.showResults();
    }
}

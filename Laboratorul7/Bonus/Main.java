package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Alex", 8.1f, Color.RED, PlayerType.RANDOM);
        Player player2 = new Player("Alin", 8.77f, Color.GREEN, PlayerType.RANDOM);
        Player player3 = new Player("Constantin", 9.79f, Color.YELLOW, PlayerType.RANDOM);
        Player player4 = new Player("Elena", 8.68f,Color.GRAY, PlayerType.INTERFACEHUMAN);
        GameBoard gameBoard = new GameBoard();
        gameBoard.generateGameBoard(5);
        Game game = new Game(gameBoard, 1);
        game.addPlayer(player1);game.addPlayer(player2);game.addPlayer(player3);game.addPlayer(player4);
        game.play();
        // game.showPlayersLists();
        // game.showWinnerMostEven();
        // game.showWinnerSmallestDifference();

    }


}

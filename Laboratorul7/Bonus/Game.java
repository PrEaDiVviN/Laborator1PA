package com.company;


import javafx.application.Application;
import javafx.stage.Stage;
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

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;


public class Game  extends Application implements Playable  {

    public volatile int turn;
    private GameBoard gameBoard;
    private Game game = this;
    private List <Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Game() {super();}

    public Game(GameBoard gameBoard, int firstToStart) {
        this.gameBoard = gameBoard;
        this.turn = firstToStart - 1;
    }

    public void showPlayersLists() {
        for (Player player : players) {
            player.printBiggestList();
        }
    }

    public void showWinnerLongestList() {
        int longestList = Integer.MIN_VALUE;
        String playerName = "";
        List<Token> listWinner = new ArrayList<>();
        for (Player player : players) {
            for (List<Token> list: player.getStatus())
                if(list.size() > longestList) {
                    longestList = list.size();
                    listWinner = list;
                    playerName = player.getName();
                }
        }
        System.out.println("Player-ul castigator este " + playerName + " cu o lista de lungime " + longestList + " !");
        for (Token t: listWinner) {
            System.out.print(t.getPair().toString());
        }
    }

    public void showWinnerMostEven() {
        int mostEven = Integer.MIN_VALUE;
        String playerName = "";
        List<Token> listWinner = new ArrayList<>();
        for (Player player : players) {
            int actualEven = 0;
            for (List<Token> list: player.getStatus()) {
                for (Token t : list) {
                    if (t.getPair().getFirst() % 2 == 0)
                        actualEven++;
                    if (t.getPair().getSecond() % 2 == 0)
                        actualEven++;
                }
                if (actualEven > mostEven) {
                    mostEven = actualEven;
                    listWinner = list;
                    playerName = player.getName();
                }
            }
        }
        System.out.println("Player-ul care are cele mai multe numere pare este " + playerName + " cu numarul numerelor pare " + mostEven + " !");
        System.out.println("Aceasta este lista lui: ");
        for (Token t: listWinner) {
            System.out.print(t.getPair().toString());
        }
    }

    public void showWinnerSmallestDifference() {
        int smallestDifference = Integer.MAX_VALUE;
        String playerName = "";
        List<Token> listWinner = new ArrayList<>();
        for (Player player : players) {
            int actualDifference = 0;
            List<Token> list = player.getStatus().get(0);
            for (Token t : list) {
                actualDifference = actualDifference + abs(t.getPair().getFirst()-t.getPair().getSecond());
            }
            if (actualDifference < smallestDifference) {
                smallestDifference = actualDifference;
                listWinner = list;
                playerName = player.getName();
            }
        }
        System.out.println("Player-ul care are tokene cu diferentele cele mai mici este " + playerName + " cu suma diferentelor " + smallestDifference + " !");
        System.out.println("Aceasta este lista lui: ");
        for (Token t: listWinner) {
            System.out.print(t.getPair().toString());
        }
    }

    @Override
    public void play() {
        synchronized (this) {
            PlayGame playing = new PlayGame(this.gameBoard, this);
            new Thread(playing).start();
            new Thread(playing).start();
            new Thread(playing).start();
            new Thread(playing).start();
            //Thread daemon = new Thread(new runningDaemonThread(this.gameBoard));
            //daemon.setDaemon(true);
            //daemon.start();
            MoveType.game = game;
            MoveType.gameBoard = gameBoard;
            launch();
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        javafx.scene.control.Button btn = new Button();
        btn.setText("Extrage tokenul");

        javafx.scene.text.Text label = new Text();
        label.setText("Introduceti indexul tokenului pe care doriti sa il extrageti: ");
        label.setLayoutX(100);
        label.setLayoutY(200);
        javafx.scene.control.TextArea txt = new TextArea();
        txt.setMaxWidth(200);
        txt.setMaxHeight(50);
        txt.setLayoutY(500/2 - 50/2);
        txt.setLayoutX(500/2 - 200/2);
        javafx.scene.text.Text ramainingTokens = new Text();
        ramainingTokens.setLayoutX(100);
        ramainingTokens.setLayoutY(300);

        String context = new String("");
        int newline = 0;
        for (Token t: MoveType.gameBoard.getRepresentationBoard()) {
            context = context + t.getPair();
            newline ++;
            if(newline > 0 && newline%10 == 0)
                context = context + "\n";
        }
        ramainingTokens.setText(context);


        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(MoveType.gameBoard.getRepresentationBoard().size() != 0) {
                    MoveType.makeMoveInterfaceUser(Integer.parseInt(txt.getText()));
                    while (MoveType.game.turn != 3) ;
                }
                if (MoveType.gameBoard.getRepresentationBoard().size() > 0) {
                    String context = new String("");
                    int newline = 0;
                    for (Token t : MoveType.gameBoard.getRepresentationBoard()) {
                        context = context + t.getPair();
                        newline++;
                        if (newline > 0 && newline % 10 == 0)
                            context = context + "\n";
                    }
                    ramainingTokens.setText(context);
                }
                else
                    ramainingTokens.setText("");
                if(btn.getText().compareTo("Afiseaza castigatorul!") == 0) {
                    int longestList = Integer.MIN_VALUE;
                    String playerName = "";
                    List<Token> listWinner = new ArrayList<>();
                    for (Player player : MoveType.game.getPlayers()) {
                        for (List<Token> list : player.getStatus())
                            if (list.size() > longestList) {
                                longestList = list.size();
                                listWinner = list;
                                playerName = player.getName();
                            }
                    }
                    int newline = 0;
                    playerName = ("Player-ul castigator este " + playerName + " cu o lista de lungime " + longestList + " !");
                    playerName = playerName + "\nAceasta este lista userului de tokene: \n";
                    for (Token t : listWinner) {
                        playerName = playerName + t.getPair().toString();
                        newline++;
                        if (newline > 0 && newline % 10 == 0)
                            playerName = playerName + "\n";
                    }
                    ramainingTokens.setText(playerName);
                }
                if(MoveType.gameBoard.getRepresentationBoard().size() == 0)
                    btn.setText("Afiseaza castigatorul!");
            }
        });
        Pane root = new Pane();
        root.getChildren().add(btn);
        root.getChildren().add(txt);
        root.getChildren().add(label);
        root.getChildren().add(ramainingTokens);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
}

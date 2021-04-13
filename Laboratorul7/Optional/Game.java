package com.company;


import java.util.ArrayList;
import java.util.List;


public class Game implements Playable {

    public volatile int turn;
    private GameBoard gameBoard;
    private List <Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

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

    @Override
    public void play() {
        synchronized (this) {
            PlayGame playing = new PlayGame(this.gameBoard, this);
            new Thread(playing).start();
            new Thread(playing).start();
            new Thread(playing).start();
            new Thread(playing).start();
            Thread daemon = new Thread(new runningDaemonThread(this.gameBoard));
            daemon.setDaemon(true);
            daemon.start();
            while (this.gameBoard.getRepresentationBoard().size() > 0) ;
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}

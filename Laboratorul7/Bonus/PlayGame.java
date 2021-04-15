package com.company;

import java.util.*;

public class PlayGame implements Runnable {
    private Game game;
    private GameBoard gameBoard;

    public PlayGame(GameBoard gameBoard, Game game) {
        this.gameBoard = gameBoard;
        this.game = game;
    }

    private void workThread0() {
        while (gameBoard.getRepresentationBoard().size() > 0) {
            synchronized (this) {
                while (!Thread.currentThread().getName().endsWith(Integer.toString(game.turn))) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(this.game.getPlayers().get(this.game.turn).type == PlayerType.COMPUTER)
                    MoveType.makeMove(game,gameBoard);
                else if (this.game.getPlayers().get(this.game.turn).type == PlayerType.HUMAN)
                    MoveType.makeMoveHumanShell(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.RANDOM)
                    MoveType.makeMoveRandom(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.EVENGAMECOMPUTER)
                    MoveType.makeMoveMostEven(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.SMALLESTDIFFERENCECOMPUTER)
                    MoveType.makeMoveSmallestDifference(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.INTERFACEHUMAN)
                    MoveType.makeMoveInterfaceUser(0);
                notifyAll();
            }
        }
    }
    void workThread1() {
        while (gameBoard.getRepresentationBoard().size() > 0) {
            synchronized (this) {
                while (!Thread.currentThread().getName().endsWith(Integer.toString(game.turn))) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(this.game.getPlayers().get(this.game.turn).type == PlayerType.COMPUTER)
                    MoveType.makeMove(game,gameBoard);
                else if (this.game.getPlayers().get(this.game.turn).type == PlayerType.HUMAN)
                    MoveType.makeMoveHumanShell(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.RANDOM)
                    MoveType.makeMoveRandom(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.EVENGAMECOMPUTER)
                    MoveType.makeMoveMostEven(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.SMALLESTDIFFERENCECOMPUTER)
                    MoveType.makeMoveSmallestDifference(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.INTERFACEHUMAN)
                    MoveType.makeMoveInterfaceUser(0);
                notifyAll();
            }
        }
    }

    void workThread2() {
        while (gameBoard.getRepresentationBoard().size() > 0) {
            synchronized (this) {
                while (!Thread.currentThread().getName().endsWith(Integer.toString(game.turn))) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(this.game.getPlayers().get(this.game.turn).type == PlayerType.COMPUTER)
                    MoveType.makeMove(game,gameBoard);
                else if (this.game.getPlayers().get(this.game.turn).type == PlayerType.HUMAN)
                    MoveType.makeMoveHumanShell(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.RANDOM)
                    MoveType.makeMoveRandom(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.EVENGAMECOMPUTER)
                    MoveType.makeMoveMostEven(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.SMALLESTDIFFERENCECOMPUTER)
                    MoveType.makeMoveSmallestDifference(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.INTERFACEHUMAN)
                    MoveType.makeMoveInterfaceUser(0);
                notifyAll();
            }
        }
    }

    void workThread3() {
        while (gameBoard.getRepresentationBoard().size() > 0) {
            synchronized (this) {
                while (!Thread.currentThread().getName().endsWith(Integer.toString(game.turn))) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(this.game.getPlayers().get(this.game.turn).type == PlayerType.COMPUTER)
                    MoveType.makeMove(game,gameBoard);
                else if (this.game.getPlayers().get(this.game.turn).type == PlayerType.HUMAN)
                    MoveType.makeMoveHumanShell(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.RANDOM)
                    MoveType.makeMoveRandom(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.EVENGAMECOMPUTER)
                    MoveType.makeMoveMostEven(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.SMALLESTDIFFERENCECOMPUTER)
                    MoveType.makeMoveSmallestDifference(game,gameBoard);
                else if(this.game.getPlayers().get(this.game.turn).type == PlayerType.INTERFACEHUMAN)
                    MoveType.makeMoveInterfaceUser(0);
                notifyAll();
            }
        }
    }

    @Override
    public void run() {
        if(Thread.currentThread().getName().endsWith("0"))
            workThread0();
        else if(Thread.currentThread().getName().endsWith("1"))
            workThread1();
        else if(Thread.currentThread().getName().endsWith("2"))
            workThread2();
        else
            workThread3();
    }

}

package com.company;

public class runningDaemonThread implements Runnable{
    private GameBoard gameBoard;
    public runningDaemonThread(GameBoard gameBoard) {
        super();
        this.gameBoard = gameBoard;
    }
    @Override
    public void run() {
        long timeStart = System.currentTimeMillis();
        long timeContinue = 0;
        long lastDifference = 0;
        while(timeContinue - timeStart < 3000000) {
            timeContinue = System.currentTimeMillis();
            if((lastDifference + 1000) <= (timeContinue - timeStart) && (gameBoard.getRepresentationBoard().size() > 0)) {
                //System.out.println("Au trecut: " + ((timeContinue - timeStart) / 1000) + " secunde de cand a inceput executia programului.");
                lastDifference = timeContinue - timeStart;
            }
        }
        System.err.println("Error: the execution of the program took too long!");
        System.exit(-1);
    }
}

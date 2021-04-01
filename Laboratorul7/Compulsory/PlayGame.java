package com.company;

import java.util.*;

public class PlayGame implements Runnable {
    private Game game;
    private GameBoard gameBoard;
    private Player player;

    public PlayGame(Player player, GameBoard gameBoard, Game game) {
        this.player = player;
        this.gameBoard = gameBoard;
        this.game = game;
    }

    private void makeMove() {
        if (gameBoard.getRepresentationBoard().size() > 0) {
            if (player.getStatus().size() == 0) { //the player has zero tokens, so he will receive a random token
                Random rand = new Random();
                int tokenPosition = rand.nextInt(gameBoard.getRepresentationBoard().size());
                List<Token> newList = Collections.synchronizedList(new ArrayList<>());
                newList.add(gameBoard.getRepresentationBoard().get(tokenPosition));
                player.getStatus().add(newList);
                gameBoard.getRepresentationBoard().remove(tokenPosition);
            } else {//trying to add tokens that match either the first number in the current sequence of tokens or the last number
                /*
                 * (1,2) ; (2,3) current player status
                 * representationBoard (3,5) , (2,3) , (3,5)
                 * => extracting (3,5) because its first value matches the list other
                 * */
                boolean added = false;
                for (List<Token> actualList : player.getStatus()) {
                    Token lastToken = actualList.get(actualList.size() - 1);
                    for (Token token : gameBoard.getRepresentationBoard())
                        if (token.getPair().getFirst() == lastToken.getPair().getSecond()) {
                            actualList.add(token);
                            gameBoard.getRepresentationBoard().remove(token);
                            added = true;
                            break;
                        }
                    if (added)
                        break;
                    Token firstToken = actualList.get(0);
                    if (!firstToken.equals(lastToken)) {
                        for (Token token : gameBoard.getRepresentationBoard())
                            if (token.getPair().getSecond() == firstToken.getPair().getFirst()) {
                                actualList.add(0, token);
                                gameBoard.getRepresentationBoard().remove(token);
                                added = true;
                                break;
                            }
                    }
                    if (added)
                        break;
                }
                //daca ajungem pana aici si added = false, cream o noua lista si o adaugam
                if (!added) {
                    List<Token> list = Collections.synchronizedList(new ArrayList<>());
                    Random rand = new Random();
                    int tokenPosition = rand.nextInt(gameBoard.getRepresentationBoard().size());
                    list.add(gameBoard.getRepresentationBoard().get(tokenPosition));
                    player.getStatus().add(list);
                    gameBoard.getRepresentationBoard().remove(tokenPosition);
                }

            }
        }
        game.firstToStart = this.player;
    }


    @Override
    public void run() {
        while (gameBoard.getRepresentationBoard().size() > 0) {
            while (game.firstToStart.getName().compareTo(this.player.getName()) == 0) ;
            this.makeMove();
        }
    }

}

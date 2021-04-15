package com.company;


import java.util.*;

import static java.lang.Math.abs;

public class MoveType{
    public static Game game;
    public static GameBoard gameBoard;

    public static void makeMoveRandom(Game game, GameBoard gameBoard) { // miscarle random vor fi adaugate mereu ca o lista noua
        if (gameBoard.getRepresentationBoard().size() > 0) {
            List<Token> list = Collections.synchronizedList(new ArrayList<>());
            Random rand = new Random();
            int tokenPosition = rand.nextInt(gameBoard.getRepresentationBoard().size());
            list.add(gameBoard.getRepresentationBoard().get(tokenPosition));
            game.getPlayers().get(game.turn).getStatus().add(list);
            gameBoard.getRepresentationBoard().remove(tokenPosition);

        }
        game.turn = (game.turn + 1) % 4;
    }

    public static void makeMoveHumanShell(Game game, GameBoard gameBoard) {
        if (gameBoard.getRepresentationBoard().size() > 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Aceasta este lista de tokene!");
            for (Token token : gameBoard.getRepresentationBoard()) {
                System.out.print(token.getPair());
            }
            System.out.println();
            System.out.println("Scrie indicele corespunzator tokenului pe care doresti sa il extragi");
            int position = scanner.nextInt();
            Token addToken = gameBoard.getRepresentationBoard().get(position);
            if (game.getPlayers().get(game.turn).getStatus().size() == 0) { // daca user-ul are o lista vida, atunci trebuie sa cream o lista
                List<Token> newList = Collections.synchronizedList(new ArrayList<>());
                newList.add(addToken);
                game.getPlayers().get(game.turn).getStatus().add(newList);
            } else {
                game.getPlayers().get(game.turn).getStatus().get(0).add(gameBoard.getRepresentationBoard().get(position));
                Token rmv = gameBoard.getRepresentationBoard().get(position);
            }
            gameBoard.getRepresentationBoard().remove(addToken);
        }
        game.turn = (game.turn + 1) % 4;
    }

    public static void makeMove (Game game, GameBoard gameBoard) {
        if (gameBoard.getRepresentationBoard().size() > 0) {
            if (game.getPlayers().get(game.turn).getStatus().size() == 0) { //the player has zero tokens, so he will receive a random token
                Random rand = new Random();
                int tokenPosition = rand.nextInt(gameBoard.getRepresentationBoard().size());
                List<Token> newList = Collections.synchronizedList(new ArrayList<>());
                newList.add(gameBoard.getRepresentationBoard().get(tokenPosition));
                game.getPlayers().get(game.turn).getStatus().add(newList);
                gameBoard.getRepresentationBoard().remove(tokenPosition);
            } else {//trying to add tokens that match either the first number in the current sequence of tokens or the last number
                /*
                 * (1,2) ; (2,3) current player status
                 * representationBoard (3,5) , (2,3) , (3,5)
                 * => extracting (3,5) because its first value matches the list other
                 * */
                boolean added = false;
                for (List<Token> actualList : game.getPlayers().get(game.turn).getStatus()) {
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
                    game.getPlayers().get(game.turn).getStatus().add(list);
                    gameBoard.getRepresentationBoard().remove(tokenPosition);
                }

            }
        }
        game.turn = (game.turn + 1) % 4;
    }
    public static void makeMoveMostEven (Game game, GameBoard gameBoard) {
        if (gameBoard.getRepresentationBoard().size() > 0) {
            Token wanted = gameBoard.getRepresentationBoard().get(0);
            boolean found = false;
            for (Token t: gameBoard.getRepresentationBoard())
                if (t.getPair().getFirst()%2 == 0 && t.getPair().second%2 == 0) {
                    found = true;
                    wanted = t;
                }
            if(found) {
                if(game.getPlayers().get(game.turn).getStatus().size() == 0) {
                    List<Token> newList = Collections.synchronizedList(new ArrayList<>());
                    newList.add(wanted);
                    game.getPlayers().get(game.turn).getStatus().add(newList);
                    gameBoard.getRepresentationBoard().remove(wanted);
                }
                else {
                    game.getPlayers().get(game.turn).getStatus().get(0).add(wanted);
                    gameBoard.getRepresentationBoard().remove(wanted);
                }
            }
            else {
                for (Token t: gameBoard.getRepresentationBoard())
                    if (t.getPair().getFirst()%2 == 0 || t.getPair().second%2 == 0) {
                        found = true;
                        wanted = t;
                    }
                if(found) {
                    if(game.getPlayers().get(game.turn).getStatus().size() == 0) {
                        List<Token> newList = Collections.synchronizedList(new ArrayList<>());
                        newList.add(wanted);
                        game.getPlayers().get(game.turn).getStatus().add(newList);
                        gameBoard.getRepresentationBoard().remove(wanted);
                    }
                    else {
                        game.getPlayers().get(game.turn).getStatus().get(0).add(wanted);
                        gameBoard.getRepresentationBoard().remove(wanted);
                    }
                }
                else {
                    if(game.getPlayers().get(game.turn).getStatus().size() == 0) {
                        List<Token> newList = Collections.synchronizedList(new ArrayList<>());
                        newList.add(wanted);
                        game.getPlayers().get(game.turn).getStatus().add(newList);
                        gameBoard.getRepresentationBoard().remove(wanted);
                    }
                    else {
                        game.getPlayers().get(game.turn).getStatus().get(0).add(wanted);
                        gameBoard.getRepresentationBoard().remove(wanted);
                    }
                }
            }
        }
        game.turn = (game.turn + 1) % 4;
    }

    public static void makeMoveSmallestDifference (Game game, GameBoard gameBoard) { ///cautam din tabla tokenul cu cea mai mica diferenta
        if (gameBoard.getRepresentationBoard().size() > 0) {
            Token wanted = gameBoard.getRepresentationBoard().get(0);
            int smallestDifference = abs(wanted.getPair().first - wanted.getPair().second);
            for (Token t: gameBoard.getRepresentationBoard())
                if ((t.getPair().getFirst() - t.getPair().second) < smallestDifference) {
                    smallestDifference = abs(t.getPair().getFirst() - t.getPair().getSecond());
                    wanted = t;
                }
            if(game.getPlayers().get(game.turn).getStatus().size() == 0) {
                List<Token> newList = Collections.synchronizedList(new ArrayList<>());
                newList.add(wanted);
                game.getPlayers().get(game.turn).getStatus().add(newList);
                gameBoard.getRepresentationBoard().remove(wanted);
            }
            else {
                game.getPlayers().get(game.turn).getStatus().get(0).add(wanted);
                gameBoard.getRepresentationBoard().remove(wanted);
            }
        }
        game.turn = (game.turn + 1) % 4;
    }
    public static void makeMoveInterfaceUser(int index) {
        if (gameBoard.getRepresentationBoard().size() > 0) {
            System.out.println(Thread.currentThread().getName());
            if (Thread.currentThread().getName().contains("JavaFX")) {
                if (game.getPlayers().get(game.turn).getStatus().size() == 0) {
                    List<Token> newList = Collections.synchronizedList(new ArrayList<>());
                    newList.add(gameBoard.getRepresentationBoard().get(index));
                    game.getPlayers().get(game.turn).getStatus().add(newList);
                    gameBoard.getRepresentationBoard().remove(gameBoard.getRepresentationBoard().get(index));
                } else {
                    boolean added = false;
                    for (List<Token> actualList : game.getPlayers().get(game.turn).getStatus()) {
                        Token lastToken = actualList.get(actualList.size() - 1);
                    if (gameBoard.getRepresentationBoard().get(index).getPair().getFirst() == lastToken.getPair().getSecond()) {
                        actualList.add(gameBoard.getRepresentationBoard().get(index));
                        gameBoard.getRepresentationBoard().remove(gameBoard.getRepresentationBoard().get(index));
                        added = true;
                        break;
                    }
                    if (added)
                        break;
                    Token firstToken = actualList.get(0);
                    if (!firstToken.equals(lastToken)) {
                        if (gameBoard.getRepresentationBoard().get(index).getPair().getSecond() == firstToken.getPair().getFirst()) {
                            actualList.add(0, gameBoard.getRepresentationBoard().get(index));
                            gameBoard.getRepresentationBoard().remove(gameBoard.getRepresentationBoard().get(index));
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
                        game.getPlayers().get(game.turn).getStatus().add(list);
                        gameBoard.getRepresentationBoard().remove(tokenPosition);
                    }

                }
                System.out.println("YEY");
            }
            else {
                while (game.turn == 3) ;
                System.out.println("Vreau sa ies thread joc");
                return;
            }
        }
        game.turn = (game.turn + 1) % 4;
        System.out.println("Ies -> thread player");
    }
}

package com.company;

import java.util.*;

public class GameBoard extends Board{
    List<Token> representationBoard = Collections.synchronizedList(new ArrayList<>());

    public List<Token> getRepresentationBoard() {
        return representationBoard;
    }

    public void setRepresentationBoard(List<Token> representationBoard) {
        this.representationBoard = representationBoard;
    }

    public List<Token> generateGameBoard(int limit) {
        Random rand = new Random();
        //Vom genera random un numar de tokene si apoi tokenele
        int size = rand.nextInt(limit*(limit-1) + 1);
        int currentTokens;
        if(size > ((limit *(limit-1)) / 2)) { //eliminam tokene
            currentTokens = (limit *(limit-1));
            for (int i = 1; i <= limit; i++)
                for (int j = 1; j <= limit; j++)
                    if(i != j) {
                        representationBoard.add(new Token(new Pair(i,j),rand.nextInt(size)));
                    }
            while(currentTokens > size) {
                int tokenIndex = rand.nextInt(representationBoard.size());
                representationBoard.remove(tokenIndex);
                currentTokens--;
                }
        }
        else { //adaugam tokene
            currentTokens = 0;
            while(currentTokens < size) {
                Pair pair = new Pair(1 + rand.nextInt(size),1+rand.nextInt(size));
                int value = rand.nextInt(size);
                Token token = new Token(pair,value);

                boolean include = false;
                for (Token t : representationBoard)
                    if(t.equals(token)) {
                        include = true;
                        break;
                    }
                if(!include) {
                    representationBoard.add(token);
                    currentTokens++;
                }
            }
        }
        return representationBoard;
    }

    @Override
    public void showBoard() {
        for (Token t: representationBoard) {
            System.out.println(t.toString());
        }
    }
}

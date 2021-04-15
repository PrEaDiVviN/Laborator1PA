package com.company;

import java.util.List;

public class LongestSequenceBacktracking {
    private Token[] longestList;
    private int lengthLongestList = 0;
    private Token[] actualList;
    private List<Token> providedList;
    public Token[] getLongestList() {
        return longestList;
    }

    public Token getToken(int index) {
        return longestList[index];
    }

    public LongestSequenceBacktracking(List<Token> providedList) {
        this.providedList = providedList;
        longestList = new Token[providedList.size()];
        actualList = new Token[providedList.size()];
    }
    private boolean validate(Token t, int index) {
        if (index == 0) {
            actualList[index] = t;
            return true;
        }
        else {
            boolean contine = false;
            for (int i = 0; i < index; i++)
                if(actualList[i].equals(t))
                    contine = true;
            if(contine)
                return false;
            else {
                if (t.getPair().getFirst() == actualList[index-1].getPair().getSecond()) {
                    actualList[index] = t;
                    return true;
                }
            }
        }
        return false;
    }
    public void solveBacktracking(int index) {
            for (int i = 0; i < providedList.size(); i++) {
                if (validate(providedList.get(i),index))
                    solveBacktracking(index+1);
                else
                    if(index > lengthLongestList) {
                        for (int j = 0; j < index; j++) {
                            longestList[j] = actualList[j];
                            lengthLongestList = index;
                        }
                    }
            }

    }

    public void printList(){
            System.out.println("Afisam cea mai mare lista actuala: ");
            for (int i = 0; i < longestList.length; i++) {
                System.out.print(longestList[i].getPair().toString());
            }
    }
    public void addStartToken(Token startToken) {
        actualList[0]=startToken;
    }

    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        board.generateGameBoard(5);
        board.showBoard();
        LongestSequenceBacktracking obj = new LongestSequenceBacktracking(board.getRepresentationBoard());
        obj.solveBacktracking(0);

    }
}

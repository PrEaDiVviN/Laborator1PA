package com.company;

public class Token {
    private Pair pair;
    private int value;

    public Token(Pair pair) {
        this.pair = pair;
    }

    public Token(Pair pair, int value) {
        this.pair = pair;
        this.value = value;
    }

    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pair) {
        this.pair = pair;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Token) {
            Token other = (Token) obj;
            return this.getPair().first == other.getPair().first && this.getPair().second == other.getPair().second;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Token-ul (" + this.pair.first + "," + this.pair.second + ") cu valoarea " + this.value + ".";
    }
}

package com.company;

public class Solution {

    private StringBuilder printSolution = new StringBuilder();

    public StringBuilder getPrintSolution() {
        return printSolution;
    }

    public void setPrintSolution(String printSolution) {
        this.printSolution = new StringBuilder(printSolution);
    }

    @Override
    public String toString() {
        return printSolution.toString();
    }
}

package com.company;

import java.util.Vector;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Problem {
    private Vector<Source> sources;
    private Vector<Destination> destinations ;
    private int[][] cost;

    public Problem() {
        sources = new Vector<>();
        destinations = new Vector<>();
    }

    public void addSource(Source source) {
        sources.addElement(source);
    }

    public void addDestination(Destination destination) {
        destinations.addElement(destination);
    }

    public void initializeMatrix(int [][] matrix){
        cost = matrix;
    }

    public void initializeMatrixKeyboard() throws IOException{
        cost = new int [sources.size()][destinations.size()];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < sources.size(); i++)
            for (int j = 0; j < destinations.size(); j++) {
                String number = reader.readLine();
                cost[i][j] = Integer.parseInt(number);
            }
    }

    public void printProblem() {
        System.out.print(this.toString());
    }

    @Override
    public String toString() { /*functia construieste un string in response ce va reprezenta de fapt tot ceea ce se avea tot ceea ce se afla in surse si destinatie */
        StringBuilder response = new StringBuilder("Sources:-------------------------------------------------------------\n");
        for (int i = 0 ; i < sources.size(); i++ ) {
            response.append(sources.get(i).toString());
            response.append( '\n');
        }
        response.append("Destinations:--------------------------------------------------------\n");
        for( int i = 0 ; i < destinations.size(); i++ ) {
            response.append(destinations.get(i).toString());
            response.append('\n');
        }
        response.append("Cost Matrix:---------------------------------------------------------\n");
        for(int i = 0; i < sources.size(); i++) {
            for (int j = 0; j < destinations.size(); j++) {
                response.append(cost[i][j]);
                response.append(" ");
            }
            response.append('\n');
        }
        return response.toString();
    }
}

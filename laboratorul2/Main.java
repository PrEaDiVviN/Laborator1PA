package com.company;

/**
 * This is the main entry of the programme. Here all the instances are created and the problem described in the laboratory is solved.
 * @author Hutu Alexandru Dumitru
 * @see Destination
 * @see DestinationType
 * @see Source
 * @see Warehouse
 * @see Factory
 * @see Problem
 * @see Source
 */

public class Main {
    /**
     * This main function is used for the interaction between all the classes.
     * Watch the comment to see another test of the problem.
     *
     * @param args the parameter introduced using the command line. They are not used or validated in the program in any way. Do not use!
     */

    public static void main(String[] args) {
        Source[] sources = new Source[3];
        sources[0] = new Warehouse("China", 10);
        sources[1] = new Factory("Alibaba", 35);
        sources[2] = new Factory("Aliexpress", 25);
        Destination destination1 = new Destination("KAUFLAND", DestinationType.STORE, 20);
        Destination destination2 = new Destination("LIDL", DestinationType.STORE, 25);
        Destination destination3 = new Destination("DEDEMAN", DestinationType.WAREHOUSE, 25);
        Problem P = new Problem();
        P.addSource(sources[0]);
        P.addSource(sources[1]);
        P.addSource(sources[2]);
        P.addDestination(destination1);
        P.addDestination(destination2);
        P.addDestination(destination3);
        int[][] matrix = {{2, 3, 1}, {5, 4, 8}, {5, 6, 8}};
        P.initializeMatrix(matrix);
        P.printProblem();
        Solution solution = new Solution(P);
        solution.solveProblemGreedyAlgorithm();
        solution.printSolution();
        /* Second case, when supply > demand
        Source[] sources = new Source[3];
        sources[0] = new Warehouse("China", 120);
        sources[1] = new Factory("Alibaba",20);
        sources[2] = new Factory("Aliexpress",70);
        Destination destination1 = new Destination("KAUFLAND",DestinationType.STORE,30);
        Destination destination2 = new Destination("LIDL",DestinationType.STORE,40);
        Destination destination3 = new Destination("DEDEMAN",DestinationType.WAREHOUSE,50);
        Problem P = new Problem();
        P.addSource(sources[0]);P.addSource(sources[1]);P.addSource(sources[2]);
        P.addDestination(destination1); P.addDestination(destination2);  P.addDestination(destination3);
        int [][] matrix = { {9,8,1},{2,5,1},{3,4,5} };
        P.initializeMatrix(matrix);
        P.printProblem();
        Solution solution = new Solution(P);
        solution.solveProblemGreedyAlgorithm();
        solution.printSolution();
        */
    }
}

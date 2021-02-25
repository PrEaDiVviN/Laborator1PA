package com.company;

public class Main {

    public static void main(String[] args) {
        Source source1 = new Source("China", SourceType.FACTORY, 10);
        Source source2 = new Source("Alibaba", SourceType.WAREHOUSE,35);
        Source source3 = new Source("Aliexpress", SourceType.WAREHOUSE,25);
        Destination destination1 = new Destination("KAUFLAND",DestinationType.STORE,20);
        Destination destination2 = new Destination("LIDL",DestinationType.STORE,25);
        Destination destination3 = new Destination("DEDEMAN",DestinationType.WAREHOUSE,25);
        Problem P = new Problem();
        P.addSource(source1); P.addSource(source2); P.addSource(source3);
        P.addDestination(destination1); P.addDestination(destination2);  P.addDestination(destination3);
        int [][] matrix = { {2,3,1},{5,4,8},{5,6,8} };
        P.initializeMatrix(matrix);
        P.printProblem();
    }
}

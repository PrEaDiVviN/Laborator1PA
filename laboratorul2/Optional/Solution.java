package com.company;

import java.util.Vector;

/**
 * Class which encapsulates a Problem and expresses the solution to that problem
 *
 * @author Hutu Alexandru Dumitru
 */


public class Solution {
    private Problem problem;
    private boolean solved; //Reprezinta daca o problema a fost rezolvata, astfel incat sa nu poti afisa solutia daca aceasta nu a fost rezolvata
    private StringBuilder printingSolution;

    /**
     * Prints the solution of the problem on the screen
     */
    void printSolution() {
        if (!solved) {
            System.out.println("Error: problem was not solved yet!");
            System.exit(-1);
        }
        System.out.print(printingSolution);
    }

    /**
     * Returns the problem introduced
     *
     * @return type Problem - returns the problem introduced.
     */
    public Problem getProblem() {
        return problem;
    }

    /**
     * Sets the problem introduced
     *
     * @param problem which represents the problem to be introduced and to which the user want a solution.
     */
    public void setProblem(Problem problem) {
        this.problem = problem;
        solved = false;
    }

    /**
     * Constructor which requires a problem to be solved
     *
     * @param problem the problem to be solved
     */
    public Solution(Problem problem) {
        this.problem = problem;
        solved = false;
    }

    /**
     * Solves the problem introduced by using greedy algorithms
     */
    public void solveProblemGreedyAlgorithm() {
        int[][] cost = problem.getCost();
        //Verificam ca supply >= demand, caz contrar in care problema nu are solutie
        Vector<Destination> destinations = problem.getDestinations();
        Vector<Source> sources = problem.getSources();
        int column = problem.getColumnNumber(); //numarul de destinatii
        int line = problem.getLineNumber(); // numarul de surse
        int demand = 0;
        for (int i = 0; i < column; i++)
            demand += destinations.get(i).getDemand();
        int supply = 0;
        for (int i = 0; i < line; i++)
            supply += sources.get(i).getSupply();
        if (supply < demand) {
            System.out.println("Error: the problem cannot be solved (demand > supply)!");
            System.exit(-1);
        }
        /*
         *   Aproach diferit din punct de vedere greedy daca supply == demand, intrucat acest lucru ne spune ca tot supply-ul va trebui sa fie oferit. Astfel,
         *  nu ar exista situatia in care putem da tot supply-ul de la un nod si totusi sa existe o solutie mai buna, daca oferim supply de la alt nod intrucat
         *  in mod sigur tot supply-ul trebuie oferit.
         *  Algoritm-ul se bazeaza pe acest lucru: Parcurge fiecare sursa, si atat timp cat mai avem supply pt aceasta sursa, ramanem la ea. Parcurgem
         *  matricea de cost si aflam destinatia cu costul de transport minim si care are demand > 0. Realizam transportul necesar. Daca supply-ul devine
         *  0 pentru o sursa, trecem la urmatoarea si cand am parcurs toate sursele, problema a fost rezolvata.
         */
        printingSolution = new StringBuilder();
        if (supply == demand) {

            printingSolution.append("Printing solution:----------------------------------------------------\n");
            int transport = 0;//variabilia in care calculam constul de transport
            for (int i = 0; i < line; i++) {
                //Dorim sa terminam toata marfa pt fiecare nod
                //Calculam intai nodul cu cel mai mic cost de transport

                int index = -1;
                int minim = Integer.MAX_VALUE;//inseamna valoarea minima a transportarii unei unitati gasite
                for (int j = 0; j < column; j++)
                    if (destinations.get(j).getDemand() != 0) {
                        if (minim > cost[i][j]) {
                            minim = cost[i][j];
                            index = j;
                        }
                    }
                //realizam transportul
                int substract = Integer.min(sources.get(i).getSupply(), destinations.get(index).getDemand());
                transport = transport + substract * cost[i][index];
                printingSolution.append("Sending from " + sources.get(i).getName() + " " + (i + 1) + " to " +
                        destinations.get(index).getName() + " " + (index + 1) + " " +
                        Integer.min(sources.get(i).getSupply(), destinations.get(index).getDemand()) + " units.\n");

                destinations.get(index).setDemand(destinations.get(index).getDemand() - substract);
                sources.get(i).setSupply(sources.get(i).getSupply() - substract);
                if (sources.get(i).getSupply() != 0) //Inca mai avem de oferit supply, deci ramanem la nodul curent
                    i--;
            }
            printingSolution.append("The cost of the transport is: " + transport + ".\n");
            solved = true;
        } else {
            /*
             *  Idee de rezolvare greedy: Atat timp cat demand-ul tuturor destinatiilor nu a ajuns la 0, parcurgem matricea de cost si
             *  aflam cost-ul minim de transport pentru o sursa si o destinatie cu demand-ul si supply > 0. Realizam transportul necesar.
             *  Cand  demand-ul tuturor destinatiilor a ajuns la 0, problema a fost rezolvata.
             */
            int transport = 0;//variabilia in care calculam constul de transport
            boolean repeta = true;
            int indexFrom = -1, indexTo = -1;
            printingSolution.append("Printing solution:----------------------------------------------------\n");
            while (repeta) {
                int minim = Integer.MAX_VALUE;//inseamna valoarea minima a transportarii unei unitati gasite
                for (int i = 0; i < line; i++) {
                    for (int j = 0; j < column; j++)
                        if (sources.get(i).getSupply() > 0 && destinations.get(j).getDemand() > 0)  //Inseamna ca trebuie sa verificam daca aceasta este posiblitatea minima
                            if (minim > cost[i][j]) {
                                indexFrom = i;
                                indexTo = j;
                                minim = cost[i][j];
                            }
                }
                //realizam transportul
                int substract = Integer.min(sources.get(indexFrom).getSupply(), destinations.get(indexTo).getDemand());
                transport = transport + substract * cost[indexFrom][indexTo];
                printingSolution.append("Sending from " + sources.get(indexFrom).getName() + " " + (indexFrom + 1) + " to " +
                        destinations.get(indexTo).getName() + " " + (indexTo + 1) + " " +
                        Integer.min(sources.get(indexFrom).getSupply(), destinations.get(indexTo).getDemand()) + " units.\n");

                destinations.get(indexTo).setDemand(destinations.get(indexTo).getDemand() - substract);
                sources.get(indexFrom).setSupply(sources.get(indexFrom).getSupply() - substract);
                //verificam daca mai exista noduri cu demand-ul > 0
                repeta = false;
                for (int i = 0; i < column; i++)
                    if (destinations.get(i).getDemand() > 0) {
                        repeta = true;
                        i = column + 1;
                    }
            }
            printingSolution.append("The cost of the transport is: " + transport + ".\n");
            solved = true;
        }
    }

    /**
     * Solves the problem introduced by using Vogel's Approximation Algorithm
     */

    public void solveProblemVogelsApproximation() {
        printingSolution = new StringBuilder();
        printingSolution.append("Printing solution:----------------------------------------------------\n");
        //variabila ce va tine minte costul de transport
        int transport = 0;
        //matricea de cost
        int[][] cost = problem.getCost();
        //vectori utilizati pentru retinerea destinatiilro si surselor
        Vector<Destination> destinations = problem.getDestinations();
        Vector<Source> sources = problem.getSources();
        //indexi n pt a retine numarul de linii(surse), m pt a retine numarul de coloane(destinatii)
        //utilizati pentru simplitate
        int n = problem.getLineNumber();
        int m = problem.getColumnNumber();
        //Vectori ce vor fi folositi pentru a calcula diferenta pe linie si pe coloana
        int[] rowDiference = new int[n + 1];
        int[] columnDiference = new int[m + 1];
        //Vectori ce indica daca o linie sau o coloana mai este activa, initial toate sunt active
        boolean[] activeRow = new boolean[n + 1];
        boolean[] activeColumn = new boolean[m + 1];
        for (int i = 0; i < n; i++)
            activeRow[i] = true;
        for (int i = 0; i < m; i++)
            activeColumn[i] = true;
        ///variabile ce tin minte cea mai mica valoare  si a doua cea mai mica valoare
        int leastValue = Integer.MAX_VALUE;
        int secondValue = Integer.MAX_VALUE;

        boolean repeta = true;//variabila ce spune cand se termina algoritmul
        while (repeta) {
            leastValue = Integer.MAX_VALUE;
            secondValue = Integer.MAX_VALUE;
            //Obtinem diferenta pe linii
            for (int i = 0; i < n; i++)
                if (activeRow[i]) {
                    leastValue = Integer.MAX_VALUE;
                    secondValue = Integer.MAX_VALUE;
                    for (int j = 0; j < m; j++)
                        if (activeColumn[j] && cost[i][j] <= leastValue) {
                            secondValue = leastValue;
                            leastValue = cost[i][j];
                        } else if (cost[i][j] < secondValue) {
                            secondValue = cost[i][j];
                        }
                    rowDiference[i] = secondValue - leastValue;
                }
            //Obtinem diferenta pe coloane
            for (int j = 0; j < m; j++)
                if (activeColumn[j]) {
                    leastValue = Integer.MAX_VALUE;
                    secondValue = Integer.MAX_VALUE;
                    for (int i = 0; i < n; i++)
                        if (activeRow[i] && cost[i][j] <= leastValue) {
                            secondValue = leastValue;
                            leastValue = cost[i][j];
                        } else if (cost[i][j] < secondValue) {
                            secondValue = cost[i][j];
                        }
                    columnDiference[j] = secondValue - leastValue;
                }

            //obtinem penalizarea cea mai mare si indexul sau si de asemenea unde se gaseste, linie sau coloana
            int type = 0; //1 = linie, 2 = coloana
            int penalty = Integer.MIN_VALUE; //penalizarea
            int index = 0; //indexul
            for (int i = 0; i < n; i++)
                if (activeRow[i] && rowDiference[i] >= penalty) {
                    penalty = rowDiference[i];
                    index = i;
                    type = 1;
                }
            for (int i = 0; i < m; i++)
                if (activeColumn[i] && columnDiference[i] >= penalty) {
                    penalty = columnDiference[i];
                    index = i;
                    type = 2;
                }
            //gasim celula cu valoarea minima a costului in matricea de cost
            if (type == 1) { //verificam pe linie
                int minValue = Integer.MAX_VALUE;
                int indexForTransport = -1;
                for (int j = 0; j < m; j++) {
                    if (activeColumn[j] && cost[index][j] < minValue) {
                        minValue = cost[index][j];
                        indexForTransport = j;
                    }
                }
                int substract = Integer.min(sources.get(index).getSupply(), destinations.get(indexForTransport).getDemand());
                transport = transport + substract * cost[index][indexForTransport];
                printingSolution.append("Sending from " + sources.get(index).getName() + " " + (index + 1) + " to " +
                        destinations.get(indexForTransport).getName() + " " + (indexForTransport + 1) + " " +
                        Integer.min(sources.get(index).getSupply(), destinations.get(indexForTransport).getDemand()) + " units.\n");

                destinations.get(indexForTransport).setDemand(destinations.get(indexForTransport).getDemand() - substract);
                sources.get(index).setSupply(sources.get(index).getSupply() - substract);
                //daca demand-ul curent este 0, atunci inchidem coloana ; similar pentru linie
                if (destinations.get(indexForTransport).getDemand() == 0)
                    activeColumn[indexForTransport] = false;
                if (sources.get(index).getSupply() == 0)
                    activeRow[index] = false;

            } else { ///verificam pe coloana
                int minValue = Integer.MAX_VALUE;
                int indexForTransport = -1;
                for (int i = 0; i < n; i++) {
                    if (activeRow[i] && cost[i][index] < minValue) {
                        minValue = cost[i][index];
                        indexForTransport = i;
                    }
                }
                int substract = Integer.min(sources.get(indexForTransport).getSupply(), destinations.get(index).getDemand());
                transport = transport + substract * cost[indexForTransport][index];
                printingSolution.append("Sending from " + sources.get(indexForTransport).getName() + " " + (indexForTransport + 1) + " to " +
                        destinations.get(index).getName() + " " + (index + 1) + " " +
                        Integer.min(sources.get(indexForTransport).getSupply(), destinations.get(index).getDemand()) + " units.\n");

                destinations.get(index).setDemand(destinations.get(index).getDemand() - substract);
                sources.get(indexForTransport).setSupply(sources.get(indexForTransport).getSupply() - substract);
                //daca demand-ul curent este 0, atunci inchidem coloana ; similar pentru linie
                if (destinations.get(index).getDemand() == 0)
                    activeColumn[index] = false;
                if (sources.get(indexForTransport).getSupply() == 0)
                    activeRow[indexForTransport] = false;
            }
            //verificam ca inca mai avem demand
            repeta = false;
            for (int i = 0; i < m; i++)
                if (destinations.get(i).getDemand() > 0) {
                    repeta = true;
                    i = m;
                }
        }
        printingSolution.append("The cost of the transport is: " + transport + ".\n");
        solved = true;
    }
}

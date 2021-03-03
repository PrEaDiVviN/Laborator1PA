package com.company;

import java.util.Vector;

/**
 * Class which encapsulates a Problem and expresses the solution to that problem
 *
 * @author  Hutu Alexandru Dumitru
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
    Solution(Problem problem) {
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
}

package com.company;

import java.util.Random;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Class which encapsulates the data of the problem and expresses the problem.
 *
 * @author Hutu Alexandru Dumitru
 */

public class Problem {
    private Vector<Source> sources;
    private Vector<Destination> destinations;
    private int[][] cost;

    /**
     * Constructor which initializes the sources and the destinations Vectors.
     */
    public Problem() {
        sources = new Vector<>();
        destinations = new Vector<>();
    }

    /**
     * Returns a vector of Destination which represents all the destinations added to the problem.
     *
     * @return a vector of Destination
     */
    public Vector<Destination> getDestinations() {
        return destinations;
    }

    /**
     * Sets the vector of Destination which represents all the destinations added to the problem.
     *
     * @param destinations a vector of Destination
     */
    public void setDestinations(Vector<Destination> destinations) {
        this.destinations = destinations;
    }

    /**
     * Returns a vector of Source which represents all the sources added to the problem.
     *
     * @return a vector of Source
     */
    public Vector<Source> getSources() {
        return sources;
    }

    /**
     * Sets the vector of Source which represents all the sources added to the problem.
     *
     * @param sources a vector of Source.
     */
    public void setSources(Vector<Source> sources) {
        this.sources = sources;
    }

    /**
     * Returns the number of destinations. Used for manifesting the same representation from the lab2. The destinations
     * represents the columns.
     *
     * @return int
     */
    public int getColumnNumber() {
        return destinations.size();
    }

    /**
     * Returns the number of sources. Used for manifesting the same representation from the lab2. The sources
     * represents the lines.
     *
     * @return int
     */
    public int getLineNumber() {
        return sources.size();
    }

    /**
     * Returns a reference to the cost matrix which contains the bidimensional representation of the cost of transport
     *
     * @return type int[][] representing a matrix of cost transport.
     */
    public int[][] getCost() {
        return cost;
    }

    /**
     * Sets the cost matrix to a specified matrix offered as parameter
     *
     * @param cost the matrix to set to
     */
    public void setCost(int[][] cost) {
        this.cost = cost;
    }

    /**
     * Method to add a source to the sources Vector.
     * Verifies that a source is not added twice. If that happens, prints on the screen an message explaining the
     * problem and exits the programme with -1.
     *
     * @param source the source to be added to the source Vector.
     * @see Source
     */

    public void addSource(Source source) {
        for (Source index : sources)
            if (index.equals(source)) {
                System.out.println("Error: Trying to add the same source twice!");
                System.exit(-1);
            }
        sources.addElement(source);
    }

    /**
     * Method to add a destination to the destinations Vector.
     * Verifies that a destination is not added twice. If that happens, prints on the screen an message explaining the
     * problem and exits the programme with -1.
     *
     * @param destination the destination to be added to the destinations Vector.
     * @see Destination
     */

    public void addDestination(Destination destination) {
        for (Destination index : destinations)
            if (index.equals(destination)) {
                System.out.println("Error: Trying to add the same destination twice!");
                System.exit(-1);
            }
        destinations.addElement(destination);
    }

    /**
     * Method to initialize the cost matrix of the problem
     *
     * @param matrix a reference to a bidimensional array which represents the cost matrix
     */

    public void initializeMatrix(int[][] matrix) {
        cost = matrix;
    }

    /**
     * Method to read from the keyboard the cost matrix of the problem
     *
     * @throws IOException If an input or output
     *                     exception occurred
     */

    public void initializeMatrixKeyboard() throws IOException {
        cost = new int[sources.size()][destinations.size()];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < sources.size(); i++)
            for (int j = 0; j < destinations.size(); j++) {
                String number = reader.readLine();
                cost[i][j] = Integer.parseInt(number);
            }
    }

    /**
     * Prints the text returned by the method toString which is a textual representation of the problem
     */
    public void printProblem() {
        System.out.print(this.toString());
    }

    /**
     * Generates a random instance of a problem to be solved. The number of sources, number of destination
     * the type of sources (Warehouse, Factory) and cost of transport is random.
     *
     * @param upperLimit the upper limit for the cost of transport, number of sources, and number of destination
     */
    public void generateRandomInstance(int upperLimit) {
        int totalSupply = 0; ///Supply-ul total care trebuie sa fie egal cu demand-ul
        //initializam vectorii
        sources = new Vector<>();
        destinations = new Vector<>();
        //obtinem numarul de surse
        Random rand = new Random();
        int numberSources = rand.nextInt(upperLimit) + 1;
        int numberDestination = rand.nextInt(upperLimit) + 1;
        this.cost = new int[numberSources + 1][numberDestination + 1];
        //obtinem sursele
        for (int i = 0; i < numberSources; i++) {
            int type = rand.nextInt(2); // daca obiectul adaugat este Warehouse(1) sau Factory(0)
            if (type == 1) {
                int supply = rand.nextInt(upperLimit) + 1;
                sources.add(i, new Warehouse("Firma_" + i, supply));
                totalSupply = totalSupply + supply;
            } else {
                int supply = rand.nextInt(upperLimit) + 1;
                sources.add(i, new Factory("Firma_" + i, supply));
                totalSupply = totalSupply + supply;
            }
        }
        //obtinem destinatiile
        for (int i = 0; i < numberDestination; i++) {
            while (totalSupply > 0) {
                int type = rand.nextInt(2); // daca obiectul adaugat este Warehouse(1) sau Store(0)
                if (type == 1) {
                    int demand = rand.nextInt(totalSupply) + 1;
                    destinations.add(i, new Destination("Dest_" + i, DestinationType.WAREHOUSE, demand));
                    totalSupply = totalSupply - demand;
                } else {
                    int demand = rand.nextInt(totalSupply) + 1;
                    destinations.add(i, new Destination("Dest_" + i, DestinationType.STORE, demand));
                    totalSupply = totalSupply - demand;
                }
                if (totalSupply != 0)
                    i++;
            }
            destinations.add(i, new Destination("Dest_" + i, DestinationType.WAREHOUSE, 0));
        }
        //obtinem matricea de cost
        for (int i = 0; i < numberSources; i++) {
            for (int j = 0; j < numberDestination; j++) {
                int actualCost = 1 + rand.nextInt(upperLimit);
                cost[i][j] = actualCost;
            }
        }
    }

    /**
     * Overrides toString method from Object and offers a text specific for that Problem
     *
     * @return String
     */

    @Override
    public String toString() { /*functia construieste un string in response ce va reprezenta de fapt tot ceea ce se avea tot ceea ce se afla in surse si destinatie */
        StringBuilder response = new StringBuilder("Sources:-------------------------------------------------------------\n");
        for (int i = 0; i < sources.size(); i++) {
            response.append(sources.get(i).toString());
            response.append('\n');
        }
        response.append("Destinations:--------------------------------------------------------\n");
        for (int i = 0; i < destinations.size(); i++) {
            response.append(destinations.get(i).toString());
            response.append('\n');
        }
        response.append("Cost Matrix:---------------------------------------------------------\n");
        for (int i = 0; i < sources.size(); i++) {
            for (int j = 0; j < destinations.size(); j++) {
                response.append(cost[i][j]);
                response.append(" ");
            }
            response.append('\n');
        }
        return response.toString();
    }
}

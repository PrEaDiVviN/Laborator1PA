package com.company;

/**
 * Class which encapsulates the data specific to a Destination from the problem presented in lab2.
 *
 * @author  Hutu Alexandru Dumitru
 */

public class Destination {
    private String name;
    private DestinationType type;
    private int demand;

    /**
     * Returns the demand quantity of current destination
     *
     * @return int
     */
    public int getDemand() {
        return demand;
    }

    /**
     * Sets the demand of the current destination
     *
     * @param demand the demand to be set
     */
    public void setDemand(int demand) {
        this.demand = demand;
    }

    /**
     * Returns the name of the current destination
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the current destination
     *
     * @param name the name to be set
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the destination type of the current Destination
     *
     * @return DestinationType
     * @see DestinationType
     */
    public DestinationType getType() {
        return type;
    }

    /**
     * Sets the type of the current destination
     *
     * @param type the type to be set
     */
    private void setType(DestinationType type) {
        this.type = type;
    }

    /**
     * Constructor specifying the name, the type, the demand quantity
     *
     * @param name   (String) the name of the destination
     * @param type   type of the destination
     * @param demand the quantity required by that specific destination
     * @see DestinationType
     */
    public Destination(String name, DestinationType type, int demand) {
        setName(name);
        setType(type);
        setDemand(demand);
    }

    /**
     * Overrides equals method from Object and verifies that 2 instances of class Destination are
     * equal using the toString() method.
     *
     * @param obj the object element
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Destination) {
            Destination other = (Destination) obj;
            return this.toString().equals(other.toString());
        } else
            return false;
    }

    /**
     * Overrides toString method from Object and offers a text specific for that destination
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Destination: " + getType() + " " + getName() + " and requires " + getDemand() + " units of commodity.";
    }
}

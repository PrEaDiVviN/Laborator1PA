package com.company;

/**
 * Class which encapsulates the data specific to a Source from the problem presented in lab2.
 *
 * @author  Hutu Alexandru Dumitru
 */


public abstract class Source {
    private String name;
    private int supply;

    /**
     * Returns the supply from the source
     *
     * @return int
     */
    public int getSupply() {
        return supply;
    }

    /**
     * Sets the supply for the source
     *
     * @param supply value of the supply
     */
    public void setSupply(int supply) {
        this.supply = supply;
    }

    /**
     * Returns the name of the source
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the source
     *
     * @param name the name of the source
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Constructor which requires a name and a supply value
     *
     * @param name   the name of the source
     * @param supply the supply of the source
     */
    public Source(String name, int supply) {
        setName(name);
        setSupply(supply);
    }

    /**
     * Verifies if two objects are equal by using their .toString methods.
     * This methods overrides the method from the object class
     *
     * @param obj the object to verify if they are equal.
     * @return bool value (True/False)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Source) {
            Source other = (Source) obj;
            return this.toString().equals(other.toString());
        } else
            return false;
    }

    /**
     * Overrides the toString method from the Object class
     *
     * @return String which is a representation of the source in text
     */
    @Override
    public String toString() {
        return "Source: " + getName() + " and has " + getSupply() + " units of commodity.";
    }

}
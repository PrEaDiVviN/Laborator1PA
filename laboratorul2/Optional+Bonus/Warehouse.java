package com.company;

/**
 * Class which encapsulates the data of Source and expresses a Warehouse.
 *
 * @author  Hutu Alexandru Dumitru
 */


public class Warehouse extends Source {
    /**
     * Constructor which requires a name and a supply quantity
     *
     * @param name   represents the name of the factory
     * @param supply represents the supply quantity
     */
    public Warehouse(String name, int supply) {
        super(name, supply);
    }

    /**
     * Overrides the toString method from the Source class
     */
    @Override
    public String toString() {
        return super.toString().replace("Source", "Warehouse");
    }
}

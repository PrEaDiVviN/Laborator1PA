package com.company;

public class School implements Comparable<School> {
    private String name;
    private int capacity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    School(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    School() {
    }

    @Override
    public int compareTo(School other) {
        if (other != null) {
            if (other.name != null) {
                if (this.name.equals(other.name) && (this.capacity == other.capacity))
                    return 0;
                else if (this.name.equals(other.name))
                    return (this.capacity > other.capacity) ? 1 : -1;
                else
                    return this.name.compareTo(other.name);
            }
        }
        return -1;
    }
}

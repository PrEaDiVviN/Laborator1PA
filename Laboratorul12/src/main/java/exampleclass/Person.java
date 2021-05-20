package exampleclass;

import addnot.Test;
import exceptions.ExampleException;


public class Person {
    private int id;
    private String nume;
    private String prenume;
    private int varsta;

    public Person( int id, String nume, String prenume, int varsta ) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Test
    public static void TestThis() {
        System.out.println("Testing is working fine!");
    }

    @Test
    public static void TestException() throws ExampleException {
        throw new ExampleException();
    }
}

package com.company;

public class ListCommand extends Command{
    @Override
    public void applyTo(Catalog target) throws EmptyCatalogException{
        if(target.getItems().size() == 0)
            throw new EmptyCatalogException(target.getName(),target.getPath());
        else {
            System.out.println("Urmatoarele elemente se afla in catalog: ---------------------------------------------");
            for (Item item : target.getItems())
                System.out.println(item.toString());
        }
    }
}

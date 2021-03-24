package com.company;



public class AddCommand extends Command{
    private Item item;
    public AddCommand(Item item) throws InvalidCatalogItemException, InvalidItemPathException {
        if(item.getLocation().length() == 0 ||  (!item.getLocation().contains("\\") && !item.getLocation().contains("//")) )
            throw new InvalidItemPathException(item.getLocation());
        if (item instanceof Book)
            this.item = new Book((Book) item);
        else if(item instanceof Song)
            this.item = new Song((Song) item);
        else if(item instanceof Movie)
            this.item = new Movie((Movie)item);
        else if(item instanceof Image)
            this.item = new Image((Image) item);
        else
            throw new InvalidCatalogItemException();
    }
    @Override
    public void applyTo(Catalog modify) throws InvalidItemIdException {
        if(modify.getItems().stream().filter(e -> e.getId().compareTo(item.getId())==0).count() > 0 )
            throw new InvalidItemIdException(item.getName() + "(" + item.getId() + ")");
        modify.getItems().add(item);
    }
}

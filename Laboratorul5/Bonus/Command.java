package com.company;

public abstract class Command {
    public abstract void applyTo  (Catalog modify) throws InvalidCatalogItemException, InvalidItemIdException, InvalidItemPathException ,InvalidCatalogException, EmptyCatalogException, KeyboardShellCommandException;
}

package com.company;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PlayCommand extends Command{
    private String id;
    PlayCommand(String id) {
        this.id = new String(id);
    }
    @Override
    public void applyTo(Catalog target) throws InvalidItemIdException{
        if(target.findById(id) == null)
            throw new InvalidItemIdException(target.getName());
        Item item = target.findById(id);
        if(item.getLocation().startsWith("http") || item.getLocation().startsWith("https")) {
            URI url;
            try {
                url = new URI(item.getLocation());
                Desktop.getDesktop().browse(url);
            }
            catch (URISyntaxException | IOException e) {
                System.out.println("Error in playCommand.applyTo: "+ e.getMessage());
            }
        }
        else {
            File file = new File(item.getLocation());
            try {
                Desktop.getDesktop().open(file);
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

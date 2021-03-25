package com.company;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
//import org.apache.xerces.*;

import java.io.*;

public class InfoCommand extends Command{
    private String id;
    public InfoCommand(String id) {
        this.id = id;
    }

    @Override
    public void applyTo(Catalog target) throws InvalidItemIdException {
        if( target.findById(id) == null)
            throw new InvalidItemIdException(id);
        else {
            String content = new String("");
            File file = new File(target.findById(id).getLocation() );
            InputStream input;
            try {
                input = new FileInputStream(file);
                input.readAllBytes().toString();
                Tika tika = new Tika();
                content = tika.parseToString(input);
            }
            catch ( FileNotFoundException | TikaException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            catch (IOException e) {
                System.out.println("Exception: " + e.getMessage());
            }

            System.out.println(content);
        }
    }
}

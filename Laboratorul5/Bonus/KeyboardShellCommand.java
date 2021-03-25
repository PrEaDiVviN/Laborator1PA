package com.company;
import java.nio.*;
import java.sql.Date;
import java.time.Duration;
import java.util.Locale;
import java.util.Scanner;
import java.util.Formatter;
import java.text.Format;
import java.util.SimpleTimeZone;

public class KeyboardShellCommand extends Command{
    @Override
    public  void applyTo(Catalog modify) throws KeyboardShellCommandException{
        boolean continua = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to shell-command input!-------------------------------------------------------------");
        while(continua) {
            System.out.print("Enter the type of command( add / list / play / save / load / exit ): ");
            String typeCommand = new String(scanner.nextLine());
            Command cmd;
            switch (typeCommand.toLowerCase()) {
                case "add":
                    System.out.print("Enter the id of the object: ");
                    String id = new String(scanner.nextLine());
                    id = id.replace('\n','\0');
                    System.out.print("Enter the name of the object: ");
                    String name = new String(scanner.nextLine());
                    System.out.print("Enter the location of the object: ");
                    String location = new String(scanner.nextLine());
                    boolean repeatUntilWorks = true;
                    while(repeatUntilWorks) {
                        System.out.print("Enter the type of object you want to add: ( image / book / movie / song ): ");
                        String typeElement = new String(scanner.nextLine());
                        switch (typeElement.toLowerCase()) {
                            case "image":
                                System.out.print("Enter the description: ");
                                String description = new String(scanner.nextLine());
                                System.out.print("Enter the author: ");
                                String author = new String(scanner.nextLine());
                                System.out.print("Enter the dateStamp(yyyy-mm-dd): ");
                                Date dateStamp = Date.valueOf(scanner.nextLine());
                                System.out.print("Enter the width: ");
                                int width = scanner.nextInt();
                                System.out.print("Enter the height: ");
                                int height = scanner.nextInt();
                                Image img = new Image(id,name,location,description,author,dateStamp,height,width);
                                try {
                                    cmd = new AddCommand(img);
                                    cmd.applyTo(modify);
                                }
                                catch (InvalidCatalogItemException | InvalidItemIdException | InvalidItemPathException | InvalidCatalogException | EmptyCatalogException | KeyboardShellCommandException e) {
                                   throw new KeyboardShellCommandException(e);
                                }
                                System.out.println("Image object was introduced successfully!");
                                repeatUntilWorks = false;
                                break;
                            case "book":
                                System.out.print("Enter the number of pages: ");
                                int pageNumber = scanner.nextInt();
                                System.out.print("Enter the release date(yyyy-mm-dd): ");
                                Date releaseDate1 = Date.valueOf(scanner.nextLine());
                                System.out.print("Enter the rating: ");
                                float rating = scanner.nextFloat();
                                System.out.print("Enter the name of the author: ");
                                String author1 = new String(scanner.nextLine());
                                Book book = new Book(id,name,location,pageNumber,rating,releaseDate1,author1);
                                try {
                                    cmd = new AddCommand(book);
                                    cmd.applyTo(modify);
                                }
                                catch (InvalidCatalogItemException | InvalidItemIdException | InvalidItemPathException | InvalidCatalogException | EmptyCatalogException | KeyboardShellCommandException e) {
                                    throw new KeyboardShellCommandException(e);
                                }
                                System.out.println("Image object was introduced successfully!");
                                repeatUntilWorks = false;
                                break;
                            case "song":
                                System.out.print("Enter the name of the artist: ");
                                String madeByArtist = new String(scanner.nextLine());
                                System.out.print("Enter the release date(yyyy-mm-dd): ");
                                Date releaseDate = Date.valueOf(scanner.nextLine());
                                System.out.print("Enter the duration of the song: ");
                                Duration songDuration = Duration.ofSeconds(scanner.nextInt());
                                Song song = new Song(id,name,location,songDuration,releaseDate,madeByArtist);
                                try {
                                    cmd = new AddCommand(song);
                                    cmd.applyTo(modify);
                                }
                                catch (InvalidCatalogItemException | InvalidItemIdException | InvalidItemPathException | InvalidCatalogException | EmptyCatalogException | KeyboardShellCommandException e) {
                                    throw new KeyboardShellCommandException(e);
                                }
                                System.out.println("Image object was introduced successfully!");
                                repeatUntilWorks = false;
                                break;
                            case "movie":
                                System.out.print("Enter the boxOffice value: ");
                                String boxOffice = new String(scanner.nextLine());
                                System.out.print("Enter the release date(yyyy-mm-dd): ");
                                Date releaseDate2 = Date.valueOf(scanner.nextLine());
                                System.out.print("Enter the duration of the movie: ");
                                Duration movieDuration = Duration.ofSeconds(scanner.nextInt());
                                System.out.print("Enter the rating: ");
                                float rating1 = scanner.nextFloat();
                                Movie movie = new Movie(id,name,location,movieDuration,releaseDate2,boxOffice,rating1);
                                try {
                                    cmd = new AddCommand(movie);
                                    cmd.applyTo(modify);
                                }
                                catch (InvalidCatalogItemException | InvalidItemIdException | InvalidItemPathException | InvalidCatalogException | EmptyCatalogException | KeyboardShellCommandException e) {
                                    throw new KeyboardShellCommandException(e);
                                }
                                System.out.println("Image object was introduced successfully!");
                                repeatUntilWorks = false;
                                break;
                            default:
                                System.out.println("Error -> Invalid element (Try again) ");
                        }
                        scanner.nextLine();
                    }
                    break;
                case "list":
                    cmd = new ListCommand();
                    try {
                        cmd.applyTo(modify);
                    }
                    catch (InvalidCatalogItemException | InvalidItemIdException | InvalidItemPathException | InvalidCatalogException | EmptyCatalogException | KeyboardShellCommandException e) {
                        throw new KeyboardShellCommandException(e);
                    }
                    break;
                case "play":
                    System.out.print("Enter the id of the object you want to Open: ");
                    String id1 = new String(scanner.nextLine());
                    Item itm = modify.findById(id1);
                    if(itm != null) {
                        cmd = new PlayCommand(id1);
                        try {
                            cmd.applyTo(modify);
                        }
                        catch (InvalidCatalogItemException | InvalidItemIdException | InvalidItemPathException | InvalidCatalogException | EmptyCatalogException | KeyboardShellCommandException e) {
                            throw new KeyboardShellCommandException(e);
                        }
                    }
                    else
                        System.out.println("Error -> Invalid id(Try again!) ");
                    break;
                case "save":
                    cmd = new SaveCommand();
                    try {
                        cmd.applyTo(modify);
                    }
                    catch (InvalidCatalogItemException | InvalidItemIdException | InvalidItemPathException | InvalidCatalogException | EmptyCatalogException | KeyboardShellCommandException e) {
                        throw new KeyboardShellCommandException(e);
                    }
                    System.out.println("The catalog was saved to a different file!");
                    break;
                case "load":
                    System.out.print("Enter the path of the catalog: ");
                    String path = new String(scanner.nextLine());
                    cmd = new LoadCommand(path);
                    try {
                        cmd.applyTo(modify);
                    }
                    catch (InvalidCatalogItemException | InvalidItemIdException | InvalidItemPathException | InvalidCatalogException | EmptyCatalogException | KeyboardShellCommandException e) {
                        throw new KeyboardShellCommandException(e);
                    }
                    break;
                case "exit":
                    continua = false;
                    break;
                default:
                    System.out.println("Error -> Invalid type command (Try again!) ");
                    break;
            }
        }
    }
}

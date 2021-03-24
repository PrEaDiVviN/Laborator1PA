package com.company;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.sql.Date;
import java.time.Duration;

public class Main {

    public static void main(String[] args) {
        Catalog catalog = new Catalog("My catalog", "C:\\Users\\PrEaD\\Desktop\\Anul2_semestrul2\\PA");
        var imageCar = new Image("CarImage", "Big car", "C:\\Users\\PrEaD\\Pictures\\Saved Pictures\\bigcar.png", "A awesome monster truck.", "Andrew Carson", Date.valueOf("2014-05-23"), 1920, 1920);
        var imageForest = new Image("ForestImage", "Forest with fog from mountain", "C:\\Users\\PrEaD\\Pictures\\Saved Pictures\\Forest.jpg",
                "A beautiful view of a forest with it's fox. Best for windows wallpaper", "Jaymantri", Date.valueOf("2012-03-15"), 1280, 720);
        var movieCars = new Movie("CarMovie", "Fast & Furious 7", "C:\\Users\\PrEaD\\Pictures\\Saved Pictures\\fndf7.mp4", Duration.ofSeconds(8400),
                Date.valueOf("2015-04-03"), "1,516 miliarde $", 9.2f);
        var movieAction = new Movie("ActionMovie", "Spider-Man 3", "C:\\Users\\PrEaD\\Pictures\\Saved Pictures\\spider.mp4", Duration.ofSeconds(9360),
                Date.valueOf("2007-04-16"), " 895 de milioane $", 8.6f);
        var songFirst = new Song("BestSong", "SummerTime", "https://www.youtube.com/watch?v=25N1pdzvp4c&ab_channel=K-391", Duration.ofSeconds(282),
                Date.valueOf("2014-09-05"), "K-391");
        var songSecond = new Song("GoodSong", "Better Days", "https://www.youtube.com/watch?v=VGP6JP_hAPM&ab_channel=AirwaveMusicTV", Duration.ofSeconds(188),
                Date.valueOf("2019-11-18"), "Arman Cekin & Faydee");
        var bookFantasy = new Book("FantasyBook", "Harry Potter si Piatra Filozofala",
                "https://docs.google.com/viewer?a=v&pid=sites&srcid=ZGVmYXVsdGRvbWFpbnxyb21hbmVob3Jyb3J8Z3g6NDI4MDI2MWQ4MjZjZWM4MA", 152, 9.6f,
                Date.valueOf("1997-06-30"), "J. K. Rowling");
        var bookMotivational = new Book("MotivationalBook", "Arta subtila a nepasarii",
                "https://msbook.pro/motivatisisucces/1154-arta-subtil-a-nepsrii-de-mark-manson-descarc-online-gratis-pdf.html", 241, 9.1f,
                Date.valueOf("2016-09-13"), "Mark Manson");
        Command command;
        try {
            command = new AddCommand(imageCar);
            command.applyTo(catalog);
            command = new AddCommand(imageForest);
            command.applyTo(catalog);
            command = new AddCommand(movieCars);
            command.applyTo(catalog);
            command = new AddCommand(movieAction);
            command.applyTo(catalog);
            command = new AddCommand(songFirst);
            command.applyTo(catalog);
            command = new AddCommand(songSecond);
            command.applyTo(catalog);
            command = new AddCommand(bookFantasy);
            command.applyTo(catalog);
            command = new AddCommand(bookMotivational);
            command.applyTo(catalog);
            command = new CreateReportCommand();
            command.applyTo(catalog);
        }
        catch (InvalidCatalogItemException | InvalidItemIdException | InvalidItemPathException | InvalidCatalogException | EmptyCatalogException | KeyboardShellCommandException e) {
            System.out.println(e.getMessage());
        }
/**
 * java -jar C:\Users\PrEaD\Desktop\Anul2_semestrul2\PA\Laboratorul5\com\company\Laboratorul5.jar
 */
    }
}

package com.company;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.sql.Date;
import java.time.Duration;

public class Main {

    public static void main(String[] args) {
        Main app = new Main();
        //app.testAddAndList();
        //app.testPlay();
        //app.testSave();
        app.testLoad();
    }

    private void testAddAndList() {
        Catalog catalog = new Catalog("My catalog", "C:\\Users\\PrEaD\\Desktop\\Anul2_semestrul2\\PA");
        var imageCar = new Image("CarImage", "Big car", "C:\\Users\\PrEaD\\Pictures\\Saved Pictures\\bigcar.png", "A awesome monster truck.", "Andrew Carson", Date.valueOf("2014-05-23"), 1920, 1920);
        var imageForest = new Image("ForestImage", "Forest with fog from mountain", "C:\\Users\\PrEaD\\Pictures\\Saved Pictures\\Forest.jpg", "A beautiful view of a forest with it's fox. Best for windows wallpaper", "Jaymantri", Date.valueOf("2012-03-15"), 1280, 720);
        var movieCars = new Movie("CarMovie", "Fast & Furious 7", "C:\\Users\\PrEaD\\Pictures\\Saved Pictures\\fndf7.mp4", Duration.ofSeconds(8400), Date.valueOf("2015-04-03"), "1,516 miliarde $", 9.2f);
        var movieAction = new Movie("ActionMovie", "Spider-Man 3", "C:\\Users\\PrEaD\\Pictures\\Saved Pictures\\spider.mp4", Duration.ofSeconds(9360), Date.valueOf("2007-04-16"), " 895 de milioane $", 8.6f);
        var songFirst = new Song("BestSong", "SummerTime", "https://www.youtube.com/watch?v=25N1pdzvp4c&ab_channel=K-391", Duration.ofSeconds(282), Date.valueOf("2014-09-05"), "K-391");
        var songSecond = new Song("GoodSong", "Better Days", "https://www.youtube.com/watch?v=VGP6JP_hAPM&ab_channel=AirwaveMusicTV", Duration.ofSeconds(188), Date.valueOf("2019-11-18"), "Arman Cekin & Faydee");
        var bookFantasy = new Book("FantasyBook", "Harry Potter si Piatra Filozofala", "https://docs.google.com/viewer?a=v&pid=sites&srcid=ZGVmYXVsdGRvbWFpbnxyb21hbmVob3Jyb3J8Z3g6NDI4MDI2MWQ4MjZjZWM4MA", 152, 9.6f, Date.valueOf("1997-06-30"), "J. K. Rowling");
        var bookMotivational = new Book("MotivationalBook", "Arta subtila a nepasarii", "https://msbook.pro/motivatisisucces/1154-arta-subtil-a-nepsrii-de-mark-manson-descarc-online-gratis-pdf.html", 241, 9.1f, Date.valueOf("2016-09-13"), "Mark Manson");
        catalog.addItem(imageCar);
        catalog.addItem(imageForest);
        catalog.addItem(movieCars);
        catalog.addItem(movieAction);
        catalog.addItem(songFirst);
        catalog.addItem(songSecond);
        catalog.addItem(bookFantasy);
        catalog.addItem(bookMotivational);
        catalog.list();
    }

    private void testPlay() {
        Catalog catalog = new Catalog("My catalog", "C:\\Users\\PrEaD\\Desktop\\Anul2_semestrul2\\PA\\Laboratorul5\\src\\com\\company");
        var imageForest = new Image("ForestImage", "Forest with fog from mountain", "C:\\Users\\PrEaD\\Pictures\\Saved Pictures\\Forest.jpg", "A beautiful view of a forest with it's fox. Best for windows wallpaper", "Jaymantri", Date.valueOf("2012-03-15"), 1280, 720);
        var movieAction = new Movie("ActionMovie", "Spider-Man 3", "C:\\Users\\PrEaD\\Pictures\\Saved Pictures\\spider.mp4", Duration.ofSeconds(9360), Date.valueOf("2007-04-16"), " 895 de milioane $", 8.6f);
        var songSecond = new Song("GoodSong", "Better Days", "https://www.youtube.com/watch?v=VGP6JP_hAPM&ab_channel=AirwaveMusicTV", Duration.ofSeconds(188), Date.valueOf("2019-11-18"), "Arman Cekin & Faydee");
        var bookFantasy = new Book("FantasyBook", "Harry Potter si Piatra Filozofala", "https://docs.google.com/viewer?a=v&pid=sites&srcid=ZGVmYXVsdGRvbWFpbnxyb21hbmVob3Jyb3J8Z3g6NDI4MDI2MWQ4MjZjZWM4MA", 152, 9.6f, Date.valueOf("1997-06-30"), "J. K. Rowling");

        catalog.addItem(imageForest);
        catalog.addItem(movieAction);
        catalog.addItem(songSecond);
        catalog.addItem(bookFantasy);
        try {
            catalog.play("ForestImage");
            catalog.play("FantasyBook");
            catalog.play("GoodSong");
            catalog.play("ActionMovie");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void testSave() {
        Catalog catalog = new Catalog("My catalog", "C:\\Users\\PrEaD\\Desktop\\Anul2_semestrul2\\PA\\Laboratorul5\\src\\com\\company\\Catalog.ser");
        var imageForest = new Image("ForestImage", "Forest with fog from mountain", "C:\\Users\\PrEaD\\Pictures\\Saved Pictures\\Forest.jpg", "A beautiful view of a forest with it's fox. Best for windows wallpaper", "Jaymantri", Date.valueOf("2012-03-15"), 1280, 720);
        var movieAction = new Movie("ActionMovie", "Spider-Man 3", "C:\\Users\\PrEaD\\Pictures\\Saved Pictures\\spider.mp4", Duration.ofSeconds(9360), Date.valueOf("2007-04-16"), " 895 de milioane $", 8.6f);
        var songSecond = new Song("GoodSong", "Better Days", "https://www.youtube.com/watch?v=VGP6JP_hAPM&ab_channel=AirwaveMusicTV", Duration.ofSeconds(188), Date.valueOf("2019-11-18"), "Arman Cekin & Faydee");
        var bookFantasy = new Book("FantasyBook", "Harry Potter si Piatra Filozofala", "https://docs.google.com/viewer?a=v&pid=sites&srcid=ZGVmYXVsdGRvbWFpbnxyb21hbmVob3Jyb3J8Z3g6NDI4MDI2MWQ4MjZjZWM4MA", 152, 9.6f, Date.valueOf("1997-06-30"), "J. K. Rowling");

        catalog.addItem(imageForest);
        catalog.addItem(movieAction);
        catalog.addItem(songSecond);
        catalog.addItem(bookFantasy);
        try {
            CatalogUtil.save(catalog);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void testLoad() {
        Catalog catalog;
        try {
            catalog = CatalogUtil.load("C:\\Users\\PrEaD\\Desktop\\Anul2_semestrul2\\PA\\Laboratorul5\\src\\com\\company\\Catalog.ser");
            catalog.list();
        }
        catch (InvalidCatalogException | IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
 
    }
}

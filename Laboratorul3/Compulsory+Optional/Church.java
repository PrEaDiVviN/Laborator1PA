package com.company;
import java.time.LocalTime;
public class Church extends Location implements Visitable{
    private LocalTime closingTime;
    private LocalTime openingTime;

    @Override
    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    @Override
    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }


    Church(String name, String description, String imageHref, double latitude, double longitude,LocalTime openingTime, LocalTime closingTime) {
        super(name,description,imageHref,latitude,longitude);
        setOpeningTime(openingTime);
        setClosingTime(closingTime);
    }

    public Church(){}
}

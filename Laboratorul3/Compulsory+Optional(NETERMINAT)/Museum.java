package com.company;

import java.time.LocalTime;

public class Museum extends Location implements Payable, Visitable {
    private LocalTime openingTime, closingTime;
    private double ticketPrice;

    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    @Override
    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public Museum(String name, String description, double latitude, double longitude, String imageHref, LocalTime openingTime, LocalTime closingTime, double ticketPrice) {
        super(name, description, imageHref, latitude, longitude);
        setOpeningTime(openingTime);
        setClosingTime(closingTime);
        setTicketPrice(ticketPrice);
    }

    public Museum() {
    }
}

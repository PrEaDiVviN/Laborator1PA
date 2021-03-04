package com.company;
import java.time.Duration;
import java.time.LocalTime;
public interface Visitable {
    public LocalTime getOpeningTime();
    public LocalTime getClosingTime();
    default LocalTime defaultOpeningTime() {
        return LocalTime.of(9,30);
    }
    default LocalTime defaultClosingTime() {
        return LocalTime.of(20,0);
    }
    static public Duration getVisitingDuration(LocalTime openingTime,LocalTime closingTime) {
        return Duration.between(openingTime,closingTime);
    }
}

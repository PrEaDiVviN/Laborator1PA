package com;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Locale;
import java.time.DayOfWeek;

public class Info implements Command{

    private Locale locale;

    public Info(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void execute() {
        System.out.println("Country: " +  locale.getDisplayCountry(locale));
        System.out.println("Language: " + locale.getDisplayLanguage(locale));
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale);
        System.out.println("Date: " + today.format(formatter));
        Currency currency = Currency.getInstance(locale);
        System.out.println("Currency: " + currency.getDisplayName() + " " + currency.getSymbol());
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
        String[] days = dateFormatSymbols.getWeekdays();
        System.out.print("Days: ");
        for (int i = 1; i < 8; i++) {
            System.out.print(days[i] + ", ");
        }
        System.out.println();
        String[] months = dateFormatSymbols.getMonths();
        System.out.print("Months: ");
        for (int i = 0; i < 11; i++) {
            System.out.print(months[i] + ", ");
        }
        System.out.println();
    }

}

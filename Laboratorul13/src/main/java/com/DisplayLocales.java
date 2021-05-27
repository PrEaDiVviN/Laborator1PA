package com;
import java.util.Locale;

public class DisplayLocales implements Command{
    @Override
    public void execute() {
        System.out.println("Available locales:");
        Locale available[] = Locale.getAvailableLocales();
        for(Locale locale : available) {
            System.out.println(locale.getDisplayCountry() + "\t" +locale.getDisplayLanguage(locale));
        }
    }
}

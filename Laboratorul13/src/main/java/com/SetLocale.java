package com;


import app.LocaleExplore;

import java.util.Locale;

public class SetLocale implements Command{
    private LocaleExplore localeExplore;
    private Locale setLocale;

    public SetLocale(LocaleExplore localeExplore, Locale setLocale) {
        this.localeExplore = localeExplore;
        this.setLocale = setLocale;
    }

    @Override
    public void execute() {
        localeExplore.setLocate(setLocale);
    }

}

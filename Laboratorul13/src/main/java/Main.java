import app.LocaleExplore;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        LocaleExplore localeExplore = new LocaleExplore();
        localeExplore.setLocate(new Locale("ro", "RO"));
        localeExplore.startShell();
    }
}

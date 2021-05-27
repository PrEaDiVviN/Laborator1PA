package app;

import com.Command;
import com.DisplayLocales;
import com.Info;
import com.SetLocale;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    private Locale locale;

    public void startShell() {
        String baseName = "res.Messages";
        ResourceBundle messages = ResourceBundle.getBundle(baseName, locale);
        System.out.println(messages.getString("welcome"));
        System.out.println(messages.getString("command-list"));
        Scanner keyboardScanner = new Scanner(System.in);
        boolean continua = true;
        while(continua) {
            String input = keyboardScanner.nextLine();
            switch (input) {
                case "exit": {
                    System.out.println(messages.getString("exiting"));
                    continua = false;
                }
                break;
                case "list": {
                    System.out.println(messages.getString("command-list"));
                }
                break;
                case "display": {
                    Command command = new DisplayLocales();
                    command.execute();
                }
                break;
                case "setlocale ro": {
                    Locale locale = new Locale("ro", "RO");
                    locale.forLanguageTag("ro-RO");
                    Command command = new SetLocale(this,locale);
                    command.execute();
                    messages = ResourceBundle.getBundle(baseName, locale);
                    System.out.println(messages.getString("success"));
                }
                break;
                case "setlocale en": {
                    Locale locale = new Locale("en", "US");
                    locale.forLanguageTag("en-US");
                    Command command = new SetLocale(this,locale);
                    command.execute();
                    messages = ResourceBundle.getBundle(baseName, locale);
                    System.out.println(messages.getString("success"));
                }
                break;
                case "info": {
                    Command command = new Info(this.getLocate());
                    command.execute();
                }
                default:  {
                    System.out.println(messages.getString("invalid"));
                }
            }
        }
    }

    public Locale getLocate() {
        return locale;
    }

    public void setLocate(Locale locate) {
        this.locale = locate;
    }
}

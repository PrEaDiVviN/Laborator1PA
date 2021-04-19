package compulsory.templates;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import compulsory.exceptions.NoReportException;
import compulsory.model.Employee;
import compulsory.model.Movie;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
public class ReportCreate{
    public void createForEmployees(List<Employee> report) throws NoReportException {
        // 1. Configurarea
        if(report == null)
            throw new NoReportException();

        Configuration cfg = new Configuration();

        // De unde se vor lua template-urile:
        try {
            cfg.setDirectoryForTemplateLoading( new File("C:\\Users\\PrEaD\\Desktop\\Anul2_semestrul2\\PA\\Laboratul8\\src\\main\\java\\compulsory\\templates"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2. Procesarea templatului

        // Pregatirea inputului pentru template:

        Map<String, Object> input = new HashMap<String, Object>();

        input.put("employee", report);

        // Luam template-ul
        Template template = null;
        Writer fileWriter = null;
        try {
            template = cfg.getTemplate("templateEmployee.ftl");
            // Generam output-ul si il scriem la consola
            Writer consoleWriter = new OutputStreamWriter(System.out);
            template.process(input, consoleWriter);

            // Facem un fileWriter pentru a scrie codul HTML si in fisier-ul report.html
            fileWriter = new FileWriter(new File("C:\\Users\\PrEaD\\Desktop\\Anul2_semestrul2\\PA\\Laboratul8\\src\\main\\java\\compulsory\\templates\\reportEmployees.html"));
        }
        catch (IOException |  freemarker.template.TemplateException e) {
            System.out.println(e.getMessage());
        }

        try {
            // Scriem codul in fisierul report.html
            template.process(input, fileWriter);
        }
        catch (freemarker.template.TemplateException | IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                fileWriter.close();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void createForMovies(List<Movie> report) throws NoReportException {
        // 1. Configurarea
        if(report == null)
            throw new NoReportException();

        Configuration cfg = new Configuration();

        // De unde se vor lua template-urile:
        try {
            cfg.setDirectoryForTemplateLoading( new File("C:\\Users\\PrEaD\\Desktop\\Anul2_semestrul2\\PA\\Laboratul8\\src\\main\\java\\compulsory\\templates"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2. Procesarea templatului

        // Pregatirea inputului pentru template:

        Map<String, Object> input = new HashMap<String, Object>();

        input.put("movie", report);

        // Luam template-ul
        Template template = null;
        Writer fileWriter = null;
        try {
            template = cfg.getTemplate("templateMovie.ftl");
            // Generam output-ul si il scriem la consola
            Writer consoleWriter = new OutputStreamWriter(System.out);
            template.process(input, consoleWriter);

            // Facem un fileWriter pentru a scrie codul HTML si in fisier-ul report.html
            fileWriter = new FileWriter(new File("C:\\Users\\PrEaD\\Desktop\\Anul2_semestrul2\\PA\\Laboratul8\\src\\main\\java\\compulsory\\templates\\reportMovies.html"));
        }
        catch (IOException |  freemarker.template.TemplateException e) {
            System.out.println(e.getMessage());
        }

        try {
            // Scriem codul in fisierul report.html
            template.process(input, fileWriter);
        }
        catch (freemarker.template.TemplateException | IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                fileWriter.close();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
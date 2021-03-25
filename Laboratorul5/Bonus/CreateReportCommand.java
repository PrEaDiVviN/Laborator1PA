package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
public class CreateReportCommand extends Command{
    public  void applyTo(Catalog modify) throws InvalidCatalogException{
        // 1. Configurarea
        if(modify == null)
            throw new InvalidCatalogException(" null catalog");

        Configuration cfg = new Configuration();

        // De unde se vor lua template-urile:
        cfg.setClassForTemplateLoading(CreateReportCommand.class, "Templates");

        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2. Procesarea templatului

        // Pregatirea inputului pentru template:

        Map<String, Object> input = new HashMap<String, Object>();

        input.put("title", modify.getName());
        input.put("path",modify.getPath());

        List<Item> song = modify.getItems().stream().filter(e -> e instanceof Song).collect(Collectors.toList());
        input.put("song", song);

        List<Item> book = modify.getItems().stream().filter(e -> e instanceof Book).collect(Collectors.toList());
        input.put("book",book);

        List<Item> image = modify.getItems().stream().filter(e -> e instanceof Image).collect(Collectors.toList());
        input.put("image", image);

        List<Item> movie = modify.getItems().stream().filter(e -> e instanceof Movie).collect(Collectors.toList());
        input.put("movie", movie);

        // Luam template-ul
        Template template = null;
        Writer fileWriter = null;
        try {
            template = cfg.getTemplate("Report.ftl");
            // Generam output-ul si il scriem la consola
            Writer consoleWriter = new OutputStreamWriter(System.out);
            template.process(input, consoleWriter);

            // Facem un fileWriter pentru a scrie codul HTML si in fisier-ul report.html
            fileWriter = new FileWriter(new File("C:\\Users\\PrEaD\\Desktop\\Anul2_semestrul2\\PA\\Laboratorul5\\src\\com\\company\\Templates\\report.html"));
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

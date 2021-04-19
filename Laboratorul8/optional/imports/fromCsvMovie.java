package compulsory.imports;

import com.opencsv.CSVReader;
import compulsory.model.Movie;

import java.io.FileReader;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class fromCsvMovie {
    public static List<Movie> readMovieFromCSV(String fileName) {

        List<Movie> movies = new ArrayList<>();
        Movie toAddMovie;
        String title;
        Time duration;
        Date release_date;
        int height;
        double score;
        int index = 0;

        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> read = reader.readAll();
            for (String [] line: read) {
                if (index != 0) {
                    title = line[1];
                    //parsing the date
                    String date = line[4];
                    if(line[4].contains("/")) {
                        String mm = "";
                        String dd = "";
                        String yyyy = "";
                        int count = 0;
                        for (int i = 0; i <= line[4].length() - 1; i--)
                            if (line[4].charAt(i) != '/') {
                                if (count == 0)
                                    mm = mm + line[4].charAt(i);
                                else if (count == 1)
                                    dd = dd + line[4].charAt(i);
                                else
                                    yyyy = yyyy + line[4].charAt(i);
                            } else
                                count++;
                        date = new String(yyyy + "-" + mm + "-" + dd);
                    }
                    else if(line[4].length() == 4)
                        date = line[4] + "-01-01";
                    release_date = Date.valueOf(date);
                    int intTime = Integer.parseInt(line[6]);
                    if(intTime%60 < 10)
                        duration = Time.valueOf("0" + (intTime/60) + ":0" + (intTime%60) + ":00" );
                    else
                        duration = Time.valueOf("0" + (intTime/60) + ":" + (intTime%60) + ":00" );
                    score = Double.parseDouble(line[14]);
                    toAddMovie = new Movie(index, title, release_date , duration, score);
                    movies.add(toAddMovie);
                }
                index++;
                if(index == 100)
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }
}

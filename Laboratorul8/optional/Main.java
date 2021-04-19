package compulsory;

import compulsory.dao.Employees;
import compulsory.dao.Genres;
import compulsory.dao.Movies;
import compulsory.exceptions.NoEmployeeException;
import compulsory.exceptions.NoGenreException;
import compulsory.exceptions.NoMovieException;
import compulsory.exceptions.NoReportException;
import compulsory.imports.fromCsvEmployee;
import compulsory.imports.fromCsvMovie;
import compulsory.model.Employee;
import compulsory.model.Genre;
import compulsory.model.Movie;
import compulsory.templates.ReportCreate;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.List;


public class Main {
    public static  void main(String args[]) {
        /* employeeDaoExamples(); */
        /* employeeCsvExample();  */
        /* movieCsvExample();     */
        /* createReportEmployeesExample() */
        Movies moviesDao = new Movies();
        List<Movie> movies = moviesDao.getDatabaseMovies();
        ReportCreate report = new ReportCreate();
        try {
            report.createForMovies(movies);
        } catch (NoReportException e) {
            e.printStackTrace();
        }
    }

    public static void createReportEmployeesExample() {
        Employees employeesDao = new Employees();
        List<Employee> employees = employeesDao.getDatabaseEmployees();
        ReportCreate report = new ReportCreate();
        try {
            report.createForEmployees(employees);
        } catch (NoReportException e) {
            e.printStackTrace();
        }
    }

    public static void movieCsvExample() {
        List<Movie> movies = fromCsvMovie.readMovieFromCSV("C:\\Users\\PrEaD\\Desktop\\Anul2_semestrul2\\PA\\Laboratul8\\src\\main\\java\\compulsory\\resources\\IMDb movies.csv");
        Movies movieDao = new Movies();
        movieDao.insertDatabaseMovie(movies);
    }

    public static void employeeCsvExample() {
        List<Employee> employees = fromCsvEmployee.readEmployeesFromCSV("C:\\Users\\PrEaD\\Desktop\\Anul2_semestrul2\\PA\\Laboratul8\\src\\main\\java\\compulsory\\resources\\IMDb names.csv");
        Employees employeesDao = new Employees();
        employeesDao.insertDatabaseEmployees(employees);
    }

    public static void employeeDaoExamples() {
                /*
        Employees employees  = new Employees();
        try {
            Employee employee = employees.getEmployeeById(0);
            System.out.println("Employee (" + employee.getId() + ", " + employee.getName() + ", " + employee.getBirthName() + ", " + employee.getHeight() + ", " + employee.getName() + ")");
        }
        catch (NoEmployeeException e) {
            e.printStackTrace();
        }
        */
        /*
        Employee employee = new Employee(0,"Gigel Andrei", "Gabor Ovidiu", 197 , "2012-04-04", "Actor");
        Employees employees = new Employees();
        try {
            employees.InsertEmployee(employee);
        }
        catch (NoEmployeeException e) {
            e.printStackTrace();
        }
         */
        /*
        Employees employees  = new Employees();
        try {
            Employee employee = employees.getEmployeeByName("Gigel Andrei");
            System.out.println("Employee (" + employee.getId() + ", " + employee.getName() + ", " + employee.getBirthName() + ", " + employee.getHeight() + ", " + employee.getName() + ")");
        }
        catch (NoEmployeeException e) {
            e.printStackTrace();
        }
        */
    }

    public static void compulsoryExamples() {
        /*
        Genre genre = new Genre(0,"Fantasy");
        Genres genres = new Genres();
        try {
            genres.InsertGenre(genre);
        } catch (NoGenreException e) {
            e.printStackTrace();
        }
         */

        /*
        Genres genres = new Genres();
        try {
            Genre result = genres.getGenreById(0);
            System.out.println("Gen: (" + result.getId() + " , " + result.getName() + ")");
        }
        catch (NoGenreException e) {
            e.printStackTrace();
        }
        */
        /*
        Genres genres = new Genres();
        try {
            Genre result = genres.getGenreByTitle("Fantasy");
            System.out.println("Gen: (" + result.getId() + " , " + result.getName() + ")");
        }
        catch (NoGenreException e) {
            e.printStackTrace();
        }
         */
        /*
        Movie movie = new Movie(0,"Spider-man", Date.valueOf("2012-04-04"), Time.valueOf("02:02:36"),7);
        Movies movies = new Movies();
        try {
            movies.InsertMovie(movie);
        }
        catch (NoMovieException e) {
            e.printStackTrace();
        }
         */
        /*
        Movies movies = new Movies();
        try {
            Movie movie = movies.getMovieById(0);
            System.out.println("Movie (" + movie.getTitle() + ", " + movie.getId() + ", " + movie.getRelease_date() + ", " + movie.getDuration() + ", " + movie.getScore() + ")");
        }
        catch (NoMovieException e) {
            e.printStackTrace();

        }
        */
        /*
        Movies movies = new Movies();
        try {
            Movie movie = movies.getMovieByTitle("Spider-man");
            System.out.println("Movie (" + movie.getTitle() + ", " + movie.getId() + ", " + movie.getRelease_date() + ", " + movie.getDuration() + ", " + movie.getScore() + ")");
        }
        catch (NoMovieException e) {
            e.printStackTrace();
        }
         */
    }
}

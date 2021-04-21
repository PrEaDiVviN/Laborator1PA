package compulsory.analyzation;

import compulsory.connection.ConnectionPool;
import compulsory.dao.Employees;
import compulsory.dao.Movies;
import compulsory.model.Employee;
import compulsory.model.Movie;

import java.sql.*;
import java.util.List;

public class ThreadUsingCustomConnection implements Runnable{

    private Connection connection;
    private boolean close = false;

    public ThreadUsingCustomConnection(Connection connection, boolean close) {
        this.connection = connection;
        this.close = close;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println(Thread.currentThread().getName() + "has started his work!");
        if(Thread.currentThread().getName().charAt(Thread.currentThread().getName().length()-1) == '0' ||
                Thread.currentThread().getName().charAt(Thread.currentThread().getName().length()-1) == '2' ||
                Thread.currentThread().getName().charAt(Thread.currentThread().getName().length()-1) == '4' ||
                Thread.currentThread().getName().charAt(Thread.currentThread().getName().length()-1) == '6' ||
                Thread.currentThread().getName().charAt(Thread.currentThread().getName().length()-1) == '7'
        ) {
            Movies moviesDao = new Movies();
            List<Movie> list = moviesDao.getDatabaseMovies(connection);
        }
        else {
            Employees employeesDao = new Employees();
            List<Employee> list = employeesDao.getDatabaseEmployees(connection);
        }

        System.out.println(Thread.currentThread().getName() + "has terminated his work!");

        if(close) {
            try {
                connection.close();
            } catch (SQLException | NullPointerException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

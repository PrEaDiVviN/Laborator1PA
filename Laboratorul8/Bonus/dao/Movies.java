package compulsory.dao;

import compulsory.connection.ConnectionSingleton;
import compulsory.exceptions.NoEmployeeException;
import compulsory.exceptions.NoMovieException;
import compulsory.model.Employee;
import compulsory.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Movies {
    private PreparedStatement insertStmt;
    private PreparedStatement getStmtById;
    private PreparedStatement getStmtByName;

    public void InsertMovie(Movie movie) throws NoMovieException {
        if(movie == null)
            throw new NoMovieException();
        else {
            String insertString = "INSERT INTO movies VALUES( ? , ?, ? , ? , ? );";
            Connection connection = ConnectionSingleton.getConnection();
            try {
                insertStmt = connection.prepareStatement(insertString);
                insertStmt.setInt(1,movie.getId());
                insertStmt.setString(2,movie.getTitle());
                insertStmt.setDate(3,movie.getRelease_date());
                insertStmt.setTime(4,movie.getDuration());
                insertStmt.setDouble(5,movie.getScore());
                insertStmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public Movie getMovieById(int id) throws  NoMovieException {
        String getById = "SELECT * FROM movies WHERE id = ?";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            getStmtById = connection.prepareStatement(getById);
            getStmtById.setInt(1,id);
            ResultSet result = getStmtById.executeQuery();
            result.next();
            return new Movie(result.getInt("id"),result.getString("titlu"),result.getDate("release_date"),result.getTime("duration"),result.getInt("score"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoMovieException();
    }

    public Movie getMovieByTitle(String title) throws  NoMovieException {
        String getByTitle = "SELECT * FROM movies WHERE titlu = ?";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            getStmtByName = connection.prepareStatement(getByTitle);
            getStmtByName.setString(1,title);
            ResultSet result = getStmtByName.executeQuery();
            result.next();
            return new Movie(result.getInt("id"),result.getString("titlu"),result.getDate("release_date"),result.getTime("duration"),result.getInt("score"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoMovieException();
    }

    public void insertDatabaseMovie(List<Movie> listMovies) {
        for (Movie currentMovie: listMovies) {
            try {
                this.InsertMovie(currentMovie);
            } catch (NoMovieException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Movie> getDatabaseMovies() {
        List <Movie> list = new ArrayList<>();
        String query = "SELECT * FROM MOVIES;";
        Movie toAddMovie;
        Connection connection = ConnectionSingleton.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                toAddMovie = new Movie(rs.getInt("id"),rs.getString("titlu"), rs.getDate("release_date"),rs.getTime("duration"),rs.getDouble("score"));
                list.add(toAddMovie);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Movie> getDatabaseMovies(Connection connection) {
        List <Movie> list = new ArrayList<>();
        String query = "SELECT * FROM MOVIES;";
        Movie toAddMovie;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                toAddMovie = new Movie(rs.getInt("id"),rs.getString("titlu"), rs.getDate("release_date"),rs.getTime("duration"),rs.getDouble("score"));
                list.add(toAddMovie);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

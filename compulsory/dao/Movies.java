package compulsory.dao;

import compulsory.connection.ConnectionSingleton;
import compulsory.exceptions.NoGenreException;
import compulsory.exceptions.NoMovieException;
import compulsory.model.Genre;
import compulsory.model.Movie;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                insertStmt.setInt(5,movie.getScore());
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
}

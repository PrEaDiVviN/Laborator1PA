package compulsory.dao;

import compulsory.connection.ConnectionSingleton;
import compulsory.exceptions.NoGenreException;
import compulsory.model.Genre;

import java.sql.*;

public class Genres {
    private PreparedStatement insertStmt;
    private PreparedStatement getStmtById;
    private PreparedStatement getStmtByName;
    public void InsertMovie(Genre genre) throws NoGenreException {
        if(genre == null)
            throw new NoGenreException();
        else {
            String insertString = "INSERT INTO genres VALUES( ? , ? );";
            Connection connection = ConnectionSingleton.getConnection();
            try {
                 insertStmt = connection.prepareStatement(insertString);
                 insertStmt.setInt(1,genre.getId());
                 insertStmt.setString(2,genre.getName());
                 insertStmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public Genre getGenreById(int id) throws  NoGenreException {
        String getById = "SELECT * FROM genres WHERE id = ?";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            getStmtById = connection.prepareStatement(getById);
            getStmtById.setInt(1,id);
            ResultSet result = getStmtById.executeQuery();
            result.next();
            return new Genre(result.getInt("id"),result.getString("titlu"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoGenreException();
    }

    public Genre getGenreByTitle(String title) throws  NoGenreException {
        String getByName = "SELECT * FROM genres WHERE titlu = ?";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            getStmtByName = connection.prepareStatement(getByName);
            getStmtByName.setString(1,title);
            ResultSet result = getStmtByName.executeQuery();
            result.next();
            return new Genre(result.getInt("id"),result.getString("titlu"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoGenreException();
    }

}

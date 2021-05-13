package com.example.demo.dao;



import com.example.demo.Connection.ConnectionSingleton;
import com.example.demo.exceptions.NoPersonException;
import com.example.demo.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    private PreparedStatement getStmt;
    private PreparedStatement getStmtById;
    private PreparedStatement insertStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement updateStmt;

    public void insertPerson(Person person) throws NoPersonException {
        if(person == null)
            throw new NoPersonException();
        else {
            String insertString = "INSERT INTO persoane(nume, prenume, varsta) VALUES( ? , ?, ? );";
            Connection connection = ConnectionSingleton.getConnection();
            try {
                insertStmt = connection.prepareStatement(insertString);
                insertStmt.setString(1,person.getNume());
                insertStmt.setString(2,person.getPrenume());
                insertStmt.setInt(3,person.getVarsta());
                insertStmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public Person getPersonById(int id) throws  NoPersonException {
        String getById = "SELECT * FROM persoane WHERE id = ?";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            getStmtById = connection.prepareStatement(getById);
            getStmtById.setInt(1,id);
            ResultSet result = getStmtById.executeQuery();
            result.next();
            return new Person(result.getInt("id"),result.getString("nume"), result.getString("prenume"),result.getInt("varsta"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoPersonException();
    }

    public List<Person> getAllPersons() throws  NoPersonException {
        String getAllPersons = "SELECT * FROM persoane";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            getStmt = connection.prepareStatement(getAllPersons);
            ResultSet result = getStmt.executeQuery();
            List<Person> listaPersoane = new ArrayList<>();
            boolean continua = result.next();
            while( continua ) {
                Person person = new Person(result.getInt("id"),result.getString("nume"),result.getString("prenume"),result.getInt("varsta"));
                listaPersoane.add(person);
                continua = result.next();
            }
            return listaPersoane;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoPersonException();
    }

    public void deletePersonByName(String name) throws NoPersonException {
        String deletePerson = "DELETE FROM persoane WHERE nume = ?";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            deleteStmt = connection.prepareStatement(deletePerson);
            deleteStmt.setString(1, name);
            deleteStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NoPersonException();
        }
    }

    public void updatePersonByName(String oldName, String newName) throws NoPersonException {
        String deletePerson = "UPDATE persoane SET nume = ? WHERE nume = ?";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            updateStmt = connection.prepareStatement(deletePerson);
            updateStmt.setString(1, newName);
            updateStmt.setString(2, oldName);
            updateStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NoPersonException();
        }
    }

}

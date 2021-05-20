package com.example.demo.dao;

import com.example.demo.Connection.ConnectionSingleton;
import com.example.demo.exceptions.NoPersonException;
import com.example.demo.exceptions.NoRelationshipException;
import com.example.demo.model.Person;
import com.example.demo.model.Relationship;
import org.apache.commons.lang3.tuple.MutablePair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RelationshipDao {
    private PreparedStatement getStmt;
    private PreparedStatement getStmt1;
    private PreparedStatement getStmtById;
    private PreparedStatement insertStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement updateStmt;

    public void insertRelationship(Relationship relationship) throws NoPersonException {
        if(relationship == null)
            throw new NoPersonException();
        else {
            String insertString = "INSERT INTO relatii(id_prima_persoana, id_a_doua_persoana, tipul_relatiei) VALUES(?, ? , ?);";
            Connection connection = ConnectionSingleton.getConnection();
            try {
                insertStmt = connection.prepareStatement(insertString);
                if(relationship.getIdPrimaPersoana() < relationship.getIdADouaPersoana()) {
                    insertStmt.setInt(1, relationship.getIdPrimaPersoana());
                    insertStmt.setInt(2, relationship.getIdADouaPersoana());
                }
                else {
                    insertStmt.setInt(2, relationship.getIdADouaPersoana());
                    insertStmt.setInt(1, relationship.getIdPrimaPersoana());
                }
                insertStmt.setString(3, relationship.getTipulRelatiei());
                insertStmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public Relationship getRelationshipById(int id) throws NoRelationshipException {
        String getById = "SELECT * FROM relatii WHERE id_rel = ?";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            getStmtById = connection.prepareStatement(getById);
            getStmtById.setInt(1,id);
            ResultSet result = getStmtById.executeQuery();
            result.next();
            return new Relationship(result.getInt("id_rel"),result.getInt("id_prima_persoana"), result.getInt("id_a_doua_persoana") , result.getString("tipul_relatiei"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoRelationshipException();
    }

    public List<MutablePair<Integer,Integer>> getAllCountRelationshipPersonById(List<MutablePair<Integer,Integer>> relatii) throws NoRelationshipException {
        String first = "SELECT COUNT(id_prima_persoana) AS numar FROM relatii WHERE id_prima_persoana = ?";
        String second = "SELECT COUNT(id_a_doua_persoana) AS numar FROM relatii WHERE id_a_doua_persoana = ?";
        Connection connection = ConnectionSingleton.getConnection();
        int index = 0;
        for (MutablePair<Integer,Integer> pair: relatii) {
            try {
                int count = 0;
                getStmt = connection.prepareStatement(first);
                getStmt.setInt(1,pair.getLeft());
                ResultSet result = getStmt.executeQuery();
                boolean continua = result.next();
                count = count + result.getInt("numar");
                getStmt1 = connection.prepareStatement(second);
                getStmt1.setInt(1,pair.getLeft());
                result = getStmt1.executeQuery();
                continua = result.next();
                count = count + result.getInt("numar");
                relatii.get(index).setRight(count);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new NoRelationshipException();
            }
            index ++;
        }
        return relatii;
    }

    public List<Relationship> getAllRelationShips() throws NoRelationshipException {
        String getAllRelationships = "SELECT * FROM relatii";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            getStmt = connection.prepareStatement(getAllRelationships);
            ResultSet result = getStmt.executeQuery();
            List<Relationship> listaRelatii = new ArrayList<>();
            boolean continua = result.next();
            while( continua ) {
                Relationship relationship = new Relationship(result.getInt("id_rel"),result.getInt("id_prima_persoana"), result.getInt("id_a_doua_persoana") , result.getString("tipul_relatiei"));
                listaRelatii.add(relationship);
                continua = result.next();
            }
            return listaRelatii;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoRelationshipException();
    }

    public List<Relationship> getPersonRelationShipsById(int id) throws NoRelationshipException {
        String getAllRelationships = "SELECT * FROM relatii WHERE id_prima_persoana = ? OR id_a_doua_persoana = ?";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            getStmt = connection.prepareStatement(getAllRelationships);
            getStmt.setInt(1,id);
            getStmt.setInt(2,id);
            ResultSet result = getStmt.executeQuery();
            List<Relationship> listaRelatii = new ArrayList<>();
            boolean continua = result.next();
            while( continua ) {
                Relationship relationship = new Relationship(result.getInt("id_rel"),result.getInt("id_prima_persoana"), result.getInt("id_a_doua_persoana") , result.getString("tipul_relatiei"));
                listaRelatii.add(relationship);
                continua = result.next();
            }
            return listaRelatii;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoRelationshipException();
    }

    public void deleteRelationshipById(int id) throws NoRelationshipException {
        String deleteRelationship = "DELETE FROM relatii WHERE id_rel = ?";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            deleteStmt = connection.prepareStatement(deleteRelationship);
            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NoRelationshipException();
        }
    }

    public void updateRelationshipTypeById(int id, String relationshipType) throws NoRelationshipException {
        String updateRelationship = "UPDATE relatii SET tipul_relatiei = ? WHERE id_rel = ?";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            updateStmt = connection.prepareStatement(updateRelationship);
            updateStmt.setString(1, relationshipType);
            updateStmt.setInt(2, id);
            updateStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NoRelationshipException();
        }
    }
}

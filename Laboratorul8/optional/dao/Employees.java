package compulsory.dao;

import compulsory.connection.ConnectionSingleton;
import compulsory.exceptions.NoEmployeeException;
import compulsory.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Employees {
    private PreparedStatement insertStmt;
    private PreparedStatement getStmtById;
    private PreparedStatement getStmtByName;
    public void InsertEmployee(Employee employee) throws NoEmployeeException {
        if(employee == null)
            throw new NoEmployeeException();
        else {
            String insertString = "INSERT INTO employees VALUES( ? , ? , ? , ? , ?, ? );";
            Connection connection = ConnectionSingleton.getConnection();
            try {
                insertStmt = connection.prepareStatement(insertString);
                insertStmt.setInt(1,employee.getId());
                insertStmt.setString(2,employee.getName());
                insertStmt.setString(3, employee.getBirthName());
                insertStmt.setInt(4, employee.getHeight());
                insertStmt.setString(5, employee.getBirthDate());
                insertStmt.setString(6, employee.getType());
                insertStmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public Employee getEmployeeById(int id) throws  NoEmployeeException {
        String getById = "SELECT * FROM employees WHERE id = ?";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            getStmtById = connection.prepareStatement(getById);
            getStmtById.setInt(1,id);
            ResultSet result = getStmtById.executeQuery();
            result.next();
            return new Employee(result.getInt("id"), result.getString("name"), result.getString("birthName"), result.getInt("height"), result.getString("birthDate"), result.getString("type"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoEmployeeException();
    }

    public Employee getEmployeeByName(String name) throws  NoEmployeeException {
        String getByTitle = "SELECT * FROM employees WHERE id = ?";
        Connection connection = ConnectionSingleton.getConnection();
        try {
            getStmtByName = connection.prepareStatement(getByTitle);
            getStmtByName.setString(1,name);
            ResultSet result = getStmtByName.executeQuery();
            result.next();
            return new Employee(result.getInt("id"), result.getString("name"), result.getString("birthName"), result.getInt("height"), result.getString("birthDate"), result.getString("type"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NoEmployeeException();
    }

    public void insertDatabaseEmployees(List<Employee> listEmployee) {
        for (Employee currentEmployee: listEmployee) {
            try {
                this.InsertEmployee(currentEmployee);
            } catch (NoEmployeeException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Employee> getDatabaseEmployees() {
        List <Employee> list = new ArrayList<>();
        String query = "SELECT * FROM EMPLOYEES;";
        Employee toAddEmployee;
        Connection connection = ConnectionSingleton.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                toAddEmployee = new Employee(rs.getInt("id"),rs.getString("name"), rs.getString("birthName"),rs.getInt("height"),rs.getString("birthDate"),"");
                list.add(toAddEmployee);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

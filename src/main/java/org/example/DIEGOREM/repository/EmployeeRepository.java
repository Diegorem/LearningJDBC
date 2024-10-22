//En el paquete repository, creamos la clase EmployeeRepository que implementa la interfaz Repository.
//En esta clase, implementamos todos los métodos definidos en la interfaz y comenzamos a codificar cada uno de ellos.

package org.example.DIEGOREM.repository;

import org.example.DIEGOREM.model.Employee;
import org.example.DIEGOREM.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try(Statement myStamt = getConnection().createStatement();
            ResultSet myRes = myStamt.executeQuery("SELECT * FROM employees");
            ) {
            while (myRes.next()){
                Employee e = createEmployee(myRes);
                employees.add(e);
            }
        }
        return employees;
    }

    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;
        try(PreparedStatement myStamt = getConnection().prepareStatement("SELECT * FROM employees WHERE id = ?")){
            myStamt.setInt(1, id);
            try(ResultSet myRes = myStamt.executeQuery()) {
                if(myRes.next()){
                    employee = createEmployee(myRes);
                }
            }
        }
        return employee;
    }

    @Override
    public void save(Employee employee) throws SQLException {
        String sql;
        boolean isNew = (employee.getId() == null || employee.getId() == 0);

        if (isNew) {
            sql = "INSERT INTO employees(first_name, pa_surname, ma_surname, email, salary) VALUES(?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE employees SET first_name = ?, pa_surname = ?, ma_surname = ?, email = ?, salary = ? WHERE id = ?";
        }

        try (PreparedStatement myStmt = getConnection().prepareStatement(sql)) {
            myStmt.setString(1, employee.getFirst_name());
            myStmt.setString(2, employee.getPa_surname());
            myStmt.setString(3, employee.getMa_surname());
            myStmt.setString(4, employee.getEmail());
            myStmt.setFloat(5, employee.getSalary());

            if (!isNew) {
                myStmt.setInt(6, employee.getId());
            }

            int rows = myStmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Saving employee failed, no rows affected.");
            }
        } catch (SQLException e) {
            // Manejo de la excepción
            e.printStackTrace();
            throw new SQLException("Error saving employee: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement myStmt = getConnection().prepareStatement(sql)) {
            myStmt.setInt(1, id);
            int rowsAffected = myStmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Employee with id " + id + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error deleting employee: " + e.getMessage());
        }
    }

    private Employee createEmployee(ResultSet myRes) throws SQLException {
        Employee e = new Employee();
        e.setId(myRes.getInt("id"));
        e.setFirst_name(myRes.getString("first_name"));
        e.setPa_surname(myRes.getString("pa_surname"));
        e.setMa_surname(myRes.getString("ma_surname"));
        e.setEmail(myRes.getString("email"));
        e.setSalary(myRes.getFloat("salary"));
        return e;
    }
}

package org.example.DIEGOREM.main;

import org.example.DIEGOREM.model.Employee;
import org.example.DIEGOREM.repository.EmployeeRepository;
import org.example.DIEGOREM.repository.Repository;
import org.example.DIEGOREM.util.DatabaseConnection;
import org.example.DIEGOREM.view.SwingApp;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        SwingApp app = new SwingApp();
        app.setVisible(true);


        // Se utilizó el metodo try with resources para que se cierren nuestros recursos automáticamente
//        try (Connection myConn = DatabaseConnection.getInstance()){
//            Repository<Employee> repository = new EmployeeRepository();
//
//            System.out.println("-----Listando-----");
//            repository.findAll().forEach(System.out::println);
//
//            System.out.println("----Eliminando empleado----");
//            repository.delete(3);
//            System.out.println("-----Insertando empleado-----");
//            Employee e = new Employee();
//            e.setId(6);
//            e.setFirst_name("Diego");
//            e.setPa_surname("Rodriguez");
//            e.setMa_surname("Guzman");
//            e.setEmail("diegorem99@gmail.com");
//            e.setSalary(70000f);
//            repository.save(e);


//            System.out.println("----Lista de empleados actualizada----");
//            repository.findAll().forEach(System.out::println);
//        }
        
    }
}

package org.example.DIEGOREM.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Esta clase es para manejar toda la lógica de conexión a la base de datos
public class DatabaseConnection {

    private static String url = "jdbc:mysql://localhost:3306/project";
    private static String user = "root";
    private static String pass = "diegorem";

    private static Connection myConn;


    public static Connection getInstance() throws SQLException {
        if (myConn == null){
            myConn = DriverManager.getConnection(url,user,pass);
        }
        return myConn;
    }

}

package Pages;

import java.sql.Connection;
import java.sql.DriverManager;

public class ImplementedDbConnections implements DbConnections {

    static Connection conn;

    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "root", "Andile@2001");
            return conn;
        } catch (Exception sql) {

            System.out.println("Couldnt get connection");
        }
        return null;
    }
}


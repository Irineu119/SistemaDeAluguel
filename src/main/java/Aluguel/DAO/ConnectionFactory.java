package Aluguel.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionFactory {

    private static ConnectionFactory instance;

    private ConnectionFactory(){}

    public static ConnectionFactory getInstance() {
        if (instance == null)
            instance = new ConnectionFactory();

        return instance;
    }

    public Connection get() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Aluguel", "root", "univille");
    }
}
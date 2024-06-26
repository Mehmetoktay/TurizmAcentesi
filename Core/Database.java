package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance = null;
    private Connection connection = null;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/otels";
    private final String DB_USER = "mehme";
    private final String DB_PASS = "1234";

    // yukarıda tanımladığımız url, kullanıcı adı ve şifreyi kullanarak database ile bağlantı kuruyoruz.
    private Database () {
        try {
            this.connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Connection getConnection() {
        return connection;
    }

    public static Connection getInstance(){
        try {
            //eğer bağlantı kopmuş ise yeni bir bağlantı oluturuyoruz
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Database();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return instance.getConnection();

    }
}


package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class    DBConnection {
    // Costanti per la connessione al DB
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_NAME = "g_db";
    private static final String DB_URL = "jdbc:mysql://localhost";

    private String user;
    private String password;
    private Connection conn;

    /**
     * Costruttore della classe che assegna il valore null alla connessione
     */
    public DBConnection() {
        conn = null;
        user = "root";
        password = "";
    }

    /**
     * Metodo per creare una connessione col DB
     *
     * @return  Restituisce la connessione
     */
    public Connection connectDB() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL + "/" + DB_NAME, user, password);
        } catch (SQLException se) {
            se.getErrorCode();
        } catch (Exception e) {
            e.getMessage();
        }

        return conn;
    }

    /**
     * Metodo per creare una connessione con Mysql
     *
     * @return  Restituisce la connessione
     */
    public Connection connectMysql(){
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, user, password);
        } catch (SQLException se) {
            se.getErrorCode();
        } catch (Exception e) {
            e.getMessage();
        }

        return conn;
    }


    /**
     * Metodo per chiudere la connessione col DB
     * Restituisce la connessione chiusa @return
     */
    public Connection closeConnection() {
        try {
            conn.close();
        } catch (SQLException se) {
            se.getErrorCode();
        }
        return conn;
    }

    public String getDbName(){
        return DB_NAME;
    }

    public String getUser(){
        return user;
    }

    public String getPassword(){
        return password;
    }

}
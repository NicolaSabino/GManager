package Model;

import database.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by Jacopo on 26/12/14.
 * <p>
 * Classe abstract padre di tutte le classi del model (ovvero della gestione del database) <br>
 * contiene i metodi basi per usare una tabella del DB
 */
abstract public class Model {
    protected Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private DBConnection db;


    private int id_auto_increment = -1;



    /**
     * Costruttore della classe che inizializza un oggetto DBConnection per poi potersi connettere al DB
     */
    public Model() {

        db = new DBConnection();
    }

    /**
     * Metodo per aprire la connessione col DB
     */
    protected void openConnection() {
        conn = db.connectDB();
    }

    /**
     * Metodo per chiudere la connessione col DB
     */
    protected void closeConnection() {
        conn = db.closeConnection();
    }

    /**
     * Metodo che esegue una query sql per la selezione di dati
     *
     * @param sql stringa contenente la query in sql
     * @return risultato della query
     */
    protected ResultSet selectQuery(String sql) {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return rs;
    }

    /**
     * Metodo che esegue una query sql per l'update dei dati (nel dattaglio: inserimento, modifica e eliminazione)
     *
     * @param sql stringa sql
     * @return true se andata a buon fine altrimenti false
     */
    protected boolean updateQuery(String sql) {
        boolean result = false;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            result = true;
        } catch (SQLException se) {

            JOptionPane.showMessageDialog(new JFrame("errore"),"inserire una data compatibile con la deadline!");

        }

        return result;
    }

    /**
     * Metodo che esegue una query sql per l'inserimento di una tupla nel DB nel caso in cui la tabella presenti
     * una chiave int auto increment
     *
     * @param sql string sql
     * @return true se andata a buon fine altrimenti false
     */
    protected boolean insertQueryAutoIncrement(String sql) {
        boolean result = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet res = stmt.getGeneratedKeys();
            if (res.next()) {
                id_auto_increment = res.getInt(1);
            }
            result = true;
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return result;
    }

    /**
     * Metodo che fornisce l'id dell'ultimo inserimento effettuato
     *
     * @return int id
     */
    protected int getId_auto_increment() {
        return id_auto_increment;
    }

    /**
     * permette di aggiornare un campo sul db
     * @param campo
     * @param valore
     * @return
     */

    /**
     * Metodo per l'aggiornamento di una tupla sul db ad argomenti a lunghezza variabile
     * @param var
     * @return
     */
    abstract public boolean updateIntoSQL(String... var);

    /**
     * metodo astratto che permette di inserire dati nel db
     * @return
     */
    abstract public boolean insertIntoSQL()throws SQLException;

    /**
     * metodo astratto che permette di eliminare dati dal db
     * @return
     */
    abstract public boolean deleteIntoSQL();



}
package database;

//import view.general.StaticMethod;
//import view.magazzino.Magazzino;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by alessandro on 25/02/15.
 */
public class DBCreation {

    /**
     * Metodo che controlla l'esistenza del DataBase
     *
     * @return  true = presente, false = assente
     */
    public static boolean dbExist(){
        boolean result = false;

        DBConnection dbConnection;
        dbConnection = new DBConnection();
        Connection conn = null;
        Statement stmt = null;
        ResultSet query;

        try {
            conn = dbConnection.connectMysql();
            stmt = conn.createStatement();
            query = stmt.executeQuery("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'workshopManager'");

            if (query.next()){
                result = true;
            }

        } catch (SQLException se){
            se.printStackTrace();
        } finally {
            conn = dbConnection.closeConnection();
        }

        return result;
    }

    /**
     * Metodo che crea il database
     *
     * @param path  path del file sql per la creazione
     */
    /*public static void createDatabase(String path){
        List<String> fileLines = Utility.readFileLine(path);
        DBConnection dbConnection = new DBConnection();
        Connection conn = null;
        Statement stmt = null;
        String delimiter = ";";
        int lineNumber = 1;

        try {
            conn = dbConnection.connectMysql();
            stmt = conn.createStatement();
            StringBuilder stringBuilder = new StringBuilder();
            String trimmered;

            for (String line : fileLines) {
                trimmered = line.trim();

                if (!trimmered.isEmpty() && !trimmered.substring(0, 2).equals("--")){
                    stringBuilder.append(line);
                }

                if (trimmered.endsWith(delimiter)){
                    stmt.executeUpdate(stringBuilder.toString());

                    stringBuilder.setLength(0);
                }
                lineNumber ++;
            }


        } catch (SQLException se){
            System.out.println("Errore alla riga " + lineNumber);
            se.printStackTrace();
        } finally {
            conn = dbConnection.closeConnection();
        }
    }*/

}

package Model.Gruppi;

import Model.Incontro;
import Model.Model;
import Model.Sequenza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe che permette di creare gruppi di appuntamenti
 *
 */
public class GruppoAppuntamenti extends Model{

    ArrayList<Incontro> stato;

    public  GruppoAppuntamenti() {
        openConnection();

        String sql = " select * from incontro";
        this.stato = new ArrayList<Incontro>();
        ResultSet query = selectQuery(sql);

        try {
            while (query.next()) {

                Incontro app = new Incontro(query.getInt("id"));
                stato.add(stato.size(),app);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeConnection();
        }
    }


    @Override
    public boolean updateIntoSQL(String... var) {
        return false;
    }

    @Override
    public boolean insertIntoSQL() throws SQLException {
        return false;
    }

    @Override
    public boolean deleteIntoSQL() {
        return false;
    }

    public ArrayList<Incontro> getStato() {
        return stato;
    }
}

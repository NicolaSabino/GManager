package Model.Gruppi;

import Model.Attivita;
import Model.Model;
import Model.Sequenza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Permette di creare gruppi di sequenze
 */
public class GruppoSequenze extends Model{
    private ArrayList<Sequenza> stato;
    public GruppoSequenze() {
        openConnection();
        String sql=" select * from sequenza";
        this.stato= new ArrayList<>();
        ResultSet query = selectQuery(sql);
        try {
            while (query.next()){
                Sequenza app= new Sequenza(query.getString("nome"));
                stato.add(stato.size(),app);
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
    }

    @Override
    public boolean updateIntoSQL(String... var) {
        return false;
    }

    @Override
    public boolean insertIntoSQL() {
        return false;
    }

    @Override
    public boolean deleteIntoSQL() {
        return false;
    }

    public ArrayList<Sequenza> getStato() {
        return stato;
    }
}

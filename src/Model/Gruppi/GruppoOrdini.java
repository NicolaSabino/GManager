package Model.Gruppi;

import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe per creare gruppi di ordini
 */
public class GruppoOrdini extends Model{
    private ArrayList<Ordine> stato;

    public GruppoOrdini() {
        openConnection();
        this.stato= new ArrayList<Ordine>();
        String sql="select * from Ordine";
        ResultSet query = selectQuery(sql);

        try {
            while (query.next()){
                Ordine app= new Ordine(query.getInt("id"));
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

    //getter e setter
    public ArrayList<Ordine> getStato() {
        return stato;
    }

    public void setStato(ArrayList<Ordine> stato) {
        this.stato = stato;
    }
}

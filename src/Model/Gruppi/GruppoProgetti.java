package Model.Gruppi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.*;

/**
 * Permette di creare gruppi di progetti
 */
public class GruppoProgetti extends Model{
    private ArrayList<Progetto> stato;

    public GruppoProgetti(){
        openConnection();
        this.stato= new ArrayList<Progetto>();
        String sql="select * from progetto";
        ResultSet query = selectQuery(sql);

        try {
            while (query.next()){
                Progetto app= new Progetto(query.getString("nome"));
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
    public ArrayList<Progetto> getStato() {
        return stato;
    }

    public void setStato(ArrayList<Progetto> stato) {
        this.stato = stato;
    }
}

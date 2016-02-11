package Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nicola on 10/02/16.
 */
public class Ordine extends Model {
    private int Id;
    private String Matricola;
    private String Pezzo;
    private Date DataOrdine;
    private String Attivita;
    private boolean Approvazione;

    /**
     * Costruttore vuoto
     */
    public Ordine(){
        return;
    }

    /**
     * Costruttore da sql
     * @param id
     */
    public Ordine (int id){
        openConnection();
        String sql="select * from Ordine where id='"+ id + "'";
        ResultSet query= selectQuery(sql);
        try{
            query.next();
            this.setId(query.getInt("id"));
            this.setMatricola(query.getString("matricola"));
            this.setDataOrdine(query.getDate("dataordine"));
            this.setPezzo(query.getString("pezzo"));
            this.setApprovazione(query.getBoolean("approvazione"));
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
        return;
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

    //Setter and getter
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMatricola() {
        return Matricola;
    }

    public void setMatricola(String matricola) {
        Matricola = matricola;
    }

    public String getPezzo() {
        return Pezzo;
    }

    public void setPezzo(String pezzo) {
        Pezzo = pezzo;
    }

    public Date getDataOrdine() {
        return DataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        DataOrdine = dataOrdine;
    }

    public String getAttivita() {
        return Attivita;
    }

    public void setAttivita(String attivita) {
        Attivita = attivita;
    }

    public boolean isApprovazione() {
        return Approvazione;
    }

    public void setApprovazione(boolean approvazione) {
        Approvazione = approvazione;
    }
}

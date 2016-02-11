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
    private String DataOrdine;
    private String Attivita;
    private boolean Approvazione;
    private String Descrizione;

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
    public Ordine (String id){
        openConnection();
        String sql="select * from Ordine where id='"+ id + "'";
        ResultSet query= selectQuery(sql);
        try{
            if(query.next()){
                this.setId(query.getInt("id"));
                this.setMatricola(query.getString("matricola"));
                this.setDataOrdine(query.getString("dataordine"));
                this.setPezzo(query.getString("prezzo"));
                this.setApprovazione(query.getBoolean("approvazione"));
                this.setDescrizione(query.getString("descrizione"));
            }

        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
        return;
    }

    @Override
    public boolean updateIntoSQL(String... var) {
        boolean controllo=false;
        openConnection();
        String sql="update Ordine set " + var[0] + "='" + var[1]
                + "' where id='" + getId() + "'";

        if(updateQuery(sql)){
            controllo=true;
        }
        closeConnection();
        return controllo;
    }

    @Override
    public boolean insertIntoSQL() {
        openConnection();
        boolean controllo=false;
        //id è auto-incrementante quindi non va inserito
        String sql="Insert into Ordine(matricola,prezzo,dataordine,attività,Descrizione) values('"
                +   this.getMatricola()  + "','"
                +   this.getPezzo()    + "','"
                +   this.getDataOrdine()   + "','"
                +   this.getAttivita()    + "','"
                +   this.getDescrizione()         +"')";

        if(updateQuery(sql)){
            controllo=true;
        }
        closeConnection();
        return controllo;
    }

    @Override
    public boolean deleteIntoSQL() {
        openConnection();
        boolean controllo=false;

        String sql="delete from Ordine where id='" + this.getId() + "'";

        if(updateQuery(sql)){
            controllo=true;
        }
        closeConnection();
        return controllo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ordine ordine = (Ordine) o;

        return Id == ordine.Id;

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

    public String getDataOrdine() {
        return DataOrdine;
    }

    public void setDataOrdine(String dataOrdine) {
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

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }
}

package Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by nicola on 08/02/16.
 */
public class Attivita extends Model {
    private int Id;
    private double Costo;
    private String Datafine;
    private String Datafineprevista;
    private String Datainizio;
    private String Descrizione;
    private String nomesequenza;
    private int precedenza;




    /**
     * Costruttore da sql
     * @param chiave
     */
    public Attivita(String chiave){

        openConnection();
        String sql= "select * from  attività where id='"+ chiave + "'";
        ResultSet query= selectQuery(sql);
        try{
            query.next();
            this.setCosto(query.getDouble("costo"));
            this.setDatafine(query.getString("datafine"));
            this.setDatainizio(query.getString("datainizio"));
            this.setDatafineprevista(query.getString("datafineprevista"));
            this.setDescrizione(query.getString("descrizione"));
            this.setId(query.getInt("id"));
            this.setNomesequenza(query.getString("nomesequenza"));
            this.setPrecedenza(query.getInt("precedenza"));

        }catch (SQLException se){
            //se viene generato un errore è perchè la query restituisce un empty resultset
            //ovvero non vi è nessuna corrispondenza nel db con la chiave fornita

            se.printStackTrace();
        }finally {
            closeConnection();
        }
        return;
    }

    /**
     * Costruttore vuoto
     */
    public Attivita(){
        return;
    }



    @Override
    public boolean insertIntoSQL(){
        openConnection();
        boolean controllo=false;
        //id è auto-incrementante quindi non va inserito
        String sql="Insert into attività(nomesequenza,precedenza,descrizione,datainizio,datafineprevista,datafine,costo) values('"
                +   this.getNomesequenza()  + "','"
                +   this.getPrecedenza()    + "','"
                +   this.getDescrizione()   + "','"
                +   this.getDatainizio()    + "','"
                +   this.getDatafineprevista() + "','"
                +   this.getDatafine()      + "','"
                +   this.getCosto()         +"')";

        if(updateQuery(sql)){
            controllo=true;
        }
        closeConnection();
        return controllo;
    }

    /**
     * Metodo che permette di aggiornare dei valori nel db
     *
     * @return
     */
    @Override
    public boolean updateIntoSQL(String... var){

        openConnection();
        boolean conrollo=false;
        String sql="update attività set "
                + var[0] + "='" + var[1]
                +"' where id='"+ this.getId() + "'";
        if(updateQuery(sql)){
            conrollo=true;
        }
        closeConnection();

        return conrollo;
    }

    @Override
    public  boolean deleteIntoSQL(){
        openConnection();
        boolean controllo=false;

        String sql="delete from attività where id='" + this.getId() + "'";

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

        Attivita attivita = (Attivita) o;

        return Id == attivita.Id;

    }
    //getter and setter


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double costo) {
        Costo = costo;
    }

    public String getDatafine() {
        return Datafine;
    }

    public void setDatafine(String datafine) {
        Datafine = datafine;
    }

    public String getDatafineprevista() {
        return Datafineprevista;
    }

    public void setDatafineprevista(String datafineprevista) {
        Datafineprevista = datafineprevista;
    }

    public String getDatainizio() {
        return Datainizio;
    }

    public void setDatainizio(String datainizio) {
        Datainizio = datainizio;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public String getNomesequenza() {
        return nomesequenza;
    }

    public void setNomesequenza(String nomesequenza) {
        this.nomesequenza = nomesequenza;
    }

    public int getPrecedenza() {
        return precedenza;
    }

    public void setPrecedenza(int precedenza) {
        this.precedenza = precedenza;
    }
}

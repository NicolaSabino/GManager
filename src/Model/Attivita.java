package Model;

import org.jfree.data.gantt.Task;
import org.jfree.data.time.SimpleTimePeriod;
import sun.util.calendar.LocalGregorianCalendar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Classe che rappresenta la struttura dati delle attività
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
     *
     * @param chiave
     */
    public Attivita(int chiave){

        openConnection();
        String sql= "select * from  attività where id='"+ chiave + "'";
        ResultSet query= selectQuery(sql);
        try{
            if(query.next()) {
                this.setCosto(query.getDouble("costo"));
                this.setDatafine(query.getString("datafine"));
                this.setDatainizio(query.getString("datainizio"));
                this.setDatafineprevista(query.getString("datafineprevista"));
                this.setDescrizione(query.getString("descrizione"));
                this.setId(query.getInt("id"));
                this.setNomesequenza(query.getString("nomesequenza"));
                this.setPrecedenza(query.getInt("precedenza"));
            }

        }catch (SQLException se){
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
        String sql="Insert into attività(nomesequenza,descrizione,datainizio,datafineprevista,costo) values('"
                +   this.getNomesequenza()  + "','"
                +   this.getDescrizione()   + "','"
                +   this.getDatainizio()    + "','"
                +   this.getDatafineprevista() + "',"
                +   this.getCosto()         + ")";

        if(updateQuery(sql)){
            controllo=true;
        }
        closeConnection();
        return controllo;
    }

    /**
     * Metodo per inserire un'attività nel db
     *
     * @param precedenza
     * @return
     */
    public boolean insertIntoSQL(String precedenza){
        openConnection();
        boolean controllo=false;
        //id è auto-incrementante quindi non va inserito
        String sql="Insert into attività(nomesequenza,descrizione,datainizio," +
                "datafineprevista,precedenza,costo) values('"
                +   this.getNomesequenza()  + "','"
                +   this.getDescrizione()   + "','"
                +   this.getDatainizio()    + "','"
                +   this.getDatafineprevista() + "',"
                +   precedenza              + ","
                +   this.getCosto()         + ")";

        if(updateQuery(sql)){
            controllo=true;
        }
        closeConnection();
        return controllo;
    }


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

    /**
     * metodo per la conversione  della durata di una attività
     * @return
     */
    public Task getTask(){

        // considero solo DATA FINE PREVISTA

        /*
         *   PRELEVARE LE INFORMAZIONI DALLA DATA SOTTO FORMA DI STRINGA
         *
         *   aa = data.substring(0, 4);
         *   month = data.substring(5, 7);
         *   gg = data.substring(8, 10);
         */

        int giorno_inizio   = Integer.parseInt(Datainizio.substring(8,10));
        int giorno_fine     = Integer.parseInt(Datafineprevista.substring(8,10));

        int mese_inizio     = Integer.parseInt(Datainizio.substring(5,7));
        int mese_fine       = Integer.parseInt(Datafineprevista.substring(5,7));

        int anno_inizio     = Integer.parseInt(Datainizio.substring(0,4));
        int anno_fine       = Integer.parseInt(Datafineprevista.substring(0,4));


        Date inizio = date(giorno_inizio,mese_inizio-1 ,anno_inizio);
        Date fine   = date(giorno_fine,mese_fine - 1, anno_fine);

        Task t = new Task(Descrizione,  new SimpleTimePeriod(inizio,fine));

        return t;

        // return new Task(Descrizione,  new SimpleTimePeriod(date(1, Calendar.APRIL, 2001),date(5, Calendar.APRIL, 2001)));

    }

    /**
     * Utility method for creating <code>Date</code> objects.
     *
     * @param day  the date.
     * @param month  the month.
     * @param year  the year.
     *
     * @return a date.
     */
    private static Date date(final int day, final int month, final int year) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        final Date result = calendar.getTime();
        return result;

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

package Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;

/**
 * Created by nicola on 08/02/16.
 */
public class Sequenza extends Model{
    //lo stato dell'attività consiste in una collezione di attività che la appartengono

    private ArrayList<Attivita> stato;
    private double costo;
    private String nome;
    private Date fine;
    private String nomeprogetto;
    private float percentComplSeq;

    /**
     * Costruttore vuoto
     */
    public Sequenza(){
        return;
    }

    @Override
    public boolean updateIntoSQL(String... var) {
        return false;
    }

    /**
     * Costruttore con sql
     * @return
     */
    public Sequenza(String chiave){

        openConnection();
        this.stato= new ArrayList<Attivita>();

        String sql= " select a.id,a.nomesequenza,a.precedenza,a.datainizio,a.datafine,a.datafineprevista,a.costo, from attività a join sequenza s on a.nomesequenza=s.nome where sequenza.nome='"
                    + chiave + "'";

        ResultSet query = selectQuery(sql);


        try{

            Attivita appoggio=new Attivita();

            while (query.next()){
                appoggio.setId(query.getInt("id"));
                appoggio.setNomesequenza(query.getString("nomesequenza"));
                appoggio.setPrecedenza(query.getInt("precedenza"));
                appoggio.setDatainizio(query.getString("datainizio"));
                appoggio.setDatafine(query.getString("datafine"));
                appoggio.setDatafineprevista(query.getString("datafineprevista"));
                appoggio.setCosto(query.getDouble("costo"));



                stato.add(stato.size(), appoggio);
            }

        }catch (SQLException se){
            //se trovo un errore vuol dire che la select restituisce un empty result set
            //se.printStackTrace();
        }finally {
            closeConnection();
        }

        String ris="select nomeprogetto as progetto from sequenza where nome='" + chiave + "'";
        ResultSet qu=selectQuery(ris);
        try {
            qu.next();
            this.setNomeprogetto(qu.getString("progetto"));

        }catch(SQLException se){
            se.printStackTrace();
        }

        // calcolo il costo e la fine della mia sequenza
        this.setCosto(this.calcolacosto());
        this.setFine(this.calcolaFine());
        return;
    }

    /**
     * metodo che calcola il costo di una sequenza
     * @return
     */
    protected double calcolacosto(){
        double sum=0;
        for (Attivita appoggio:this.stato){
            sum += appoggio.getCosto();
        }
        return sum;
    }

    /**
     * Metodo che calcola la fine di una sequenza
     * @return
     */
    protected Date calcolaFine(){
        openConnection();
        Date risultato = null;
        String sql="select datafineprevista as data FROM attività WHERE nomesequenza='" + this.nome + "' order by datafineprevista desc LIMIT 1";
        ResultSet ris= selectQuery(sql);
        try {
            ris.next();
            risultato=ris.getDate("data");
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
        return risultato;
    }


    /**
     * Metodo che inserisce l'occeggetto stesso nel DB
     * @return
     */
    public boolean insertIntoSQL(){
        //todo da ultimare
        boolean controllo=false;
        int c=0;
        openConnection();
        String sql;


        for(Attivita appoggio:this.stato){

            sql="insert into attività(nomesequenza,precedenza,descrizione,datainizio,datafineprevista,datafine,costo) values('"
                    +   appoggio.getNomesequenza()  + "','"
                    +   appoggio.getPrecedenza()    + "','"
                    +   appoggio.getDescrizione()   + "','"
                    +   appoggio.getDatainizio()    + "','"
                    +   appoggio.getDatafineprevista() + "','"
                    +   appoggio.getDatafine()      + "','"
                    +   appoggio.getCosto()         + "')";

            if(updateQuery(sql)){
                controllo=true;

            }
        }

        String sql1="insert into Sequenza values('"
                + this.getNome() + "','"
                + this.getFine() + "','"
                + this.getNomeprogetto() + "')";

        if(updateQuery(sql1)){
            controllo=true;
        }

        closeConnection();

        return controllo;
    }

    /**
     * Metodo per comparare due oggetti sequenza
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sequenza)) return false;

        Sequenza sequenza = (Sequenza) o;

        if (getStato() != null ? !getStato().equals(sequenza.getStato()) : sequenza.getStato() != null) return false;
        return getNome() != null ? getNome().equals(sequenza.getNome()) : sequenza.getNome() == null;

    }



    /**
     * Elimina la stessa sequneza nel db
     * @return
     */
    public  boolean deleteIntoSQL(){
        openConnection();
        boolean controllo=false;
        String sql="delete from sequenza where sequenza='" + this.getNome() + "'";

        if(updateQuery(sql)){
            controllo=true;
        }

        return controllo;

    }


    //getter and setter


    public ArrayList<Attivita> getStato() {
        return stato;
    }

    public void setStato(ArrayList<Attivita> stato) {
        this.stato = stato;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getFine() {
        return fine;
    }

    public void setFine(Date fine) {
        this.fine = fine;
    }

    public String getNomeprogetto() {
        return nomeprogetto;
    }

    public void setNomeprogetto(String nomeprogetto) {
        this.nomeprogetto = nomeprogetto;
    }

    public float getPercentComplSeq() {
        return percentComplSeq;
    }

    public void setPercentComplSeq(float percentComplSeq) {
        this.percentComplSeq = percentComplSeq;
    }
}

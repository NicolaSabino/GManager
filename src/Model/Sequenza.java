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
    private String fine;
    private String nomeprogetto;
    private double percentComplSeq;

    /**
     * Costruttore vuoto
     */
    public Sequenza(){
        return;
    }



    /**
     * Costruttore con sql
     * @return
     */
    public Sequenza(String chiave){

        openConnection();
        this.stato= new ArrayList<Attivita>();



        String sql2="select * from sequenza where nome='" + chiave + "'";
        ResultSet query2 =selectQuery(sql2);

        try {
            if(query2.next()){
                this.setNome(chiave);
                this.setNomeprogetto(query2.getString("nomeprogetto"));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }

        String sql= "select * from attività where nomesequenza='" + chiave + "'";
        ResultSet query = selectQuery(sql);

        try{



            while (query.next()){
                Attivita appoggio=new Attivita();

                appoggio.setId(query.getInt("id"));
                appoggio.setNomesequenza(query.getString("nomesequenza"));
                appoggio.setDescrizione(query.getString("descrizione"));
                appoggio.setPrecedenza(query.getInt("precedenza"));
                appoggio.setDatainizio(query.getString("datainizio"));
                appoggio.setDatafine(query.getString("datafine"));
                appoggio.setDatafineprevista(query.getString("datafineprevista"));
                appoggio.setCosto(query.getDouble("costo"));

                stato.add(stato.size(), appoggio);
            }

        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }


        this.setCosto(this.calcolaCosto());
        this.setFine(this.calcolaFine());
        this.setPercentComplSeq(this.calcolaPercentualeSequenza());

    }

    /**
     * metodo per creare un insieme di attività prodotte come risultato di una ricerca
     */
    public Sequenza(String...var){

        // todo ampliare questo metodo
        this.stato=new ArrayList<Attivita>();
        openConnection();
        String sql;
        if(var[0]=="descrizione") {
            sql = "select a.id,a.nomesequenza,a.precedenza,a.descrizione,a.datainizio,a.datafineprevista,a.datafine,a.costo from attività a " +
                    "where a.descrizione like '%" + var[1] + "%'";
            ResultSet query = selectQuery(sql);

            try{
                while (query.next()){
                    Attivita appoggio=new Attivita();

                    appoggio.setId(query.getInt("id"));
                    appoggio.setNomesequenza(query.getString("nomesequenza"));
                    appoggio.setPrecedenza(query.getInt("precedenza"));
                    appoggio.setDatainizio(query.getString("datainizio"));
                    appoggio.setDatafine(query.getString("datafine"));
                    appoggio.setDatafineprevista(query.getString("datafineprevista"));
                    appoggio.setDescrizione(query.getString("descrizione"));
                    appoggio.setCosto(query.getDouble("costo"));

                    this.stato.add(stato.size(), appoggio);
                }

            }catch (SQLException se){
                se.printStackTrace();
            }finally {
                closeConnection();
            }
        }else if(var[0]=="tutteLeAttivita"){
            sql = "select a.id,a.nomesequenza,a.precedenza,a.descrizione,a.datainizio,a.datafineprevista,a.datafine,a.costo from attività a ";
            ResultSet query = selectQuery(sql);

            try{
                while (query.next()){
                    Attivita appoggio=new Attivita();

                    appoggio.setId(query.getInt("id"));
                    appoggio.setNomesequenza(query.getString("nomesequenza"));
                    appoggio.setPrecedenza(query.getInt("precedenza"));
                    appoggio.setDatainizio(query.getString("datainizio"));
                    appoggio.setDatafine(query.getString("datafine"));
                    appoggio.setDatafineprevista(query.getString("datafineprevista"));
                    appoggio.setDescrizione(query.getString("descrizione"));
                    appoggio.setCosto(query.getDouble("costo"));

                    this.stato.add(stato.size(), appoggio);
                }

            }catch (SQLException se){
                se.printStackTrace();
            }finally {
                closeConnection();
            }
        }
        return;
    }

    /**
     * metodo che calcola il costo di una sequenza
     * @return
     */
    protected double calcolaCosto(){
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
    protected String calcolaFine(){
        openConnection();
        String risultato = null;
        String sql="select datafineprevista as data FROM attività WHERE nomesequenza='" + this.nome + "' order by datafineprevista desc LIMIT 1";
        ResultSet ris= selectQuery(sql);
        try {
            if(ris.next()) {
                risultato = ris.getString("data");
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
        return risultato;
    }

    /**
     * Metodo che calcola la percentuale di completamento di una sequenza
     * @return
     */
    protected double calcolaPercentualeSequenza(){
        openConnection();
        double risultato=1;
        String sql="select percentuale_Sequenza('" + this.getNome() + "') as percentuale";
        ResultSet query =selectQuery(sql);
        try {
            if(query.next()) {
                risultato = query.getDouble("percentuale");
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
        return risultato;
    }

    @Override
    public boolean updateIntoSQL(String... var) {
        openConnection();
        boolean conrollo=false;
        String sql="update sequenza set "
                + var[0] + "='" + var[1]
                +"' where nome='"+ this.getNome() + "'";
        if(updateQuery(sql)){
            conrollo=true;
        }
        closeConnection();

        return conrollo;
    }

    @Override
    public boolean insertIntoSQL(){

        boolean controllo=false;
        openConnection();

        String sql="insert into sequenza(nome,nomeprogetto) values('"
                + this.getNome() + "','"
                + this.getNomeprogetto() + "')";

        if(updateQuery(sql)){
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
        if (o == null || getClass() != o.getClass()) return false;

        Sequenza sequenza = (Sequenza) o;

        if (stato != null ? !stato.equals(sequenza.stato) : sequenza.stato != null) return false;
        if (nome != null ? !nome.equals(sequenza.nome) : sequenza.nome != null) return false;
        return nomeprogetto != null ? nomeprogetto.equals(sequenza.nomeprogetto) : sequenza.nomeprogetto == null;

    }


    /**
     * Elimina la stessa sequneza nel db
     * @return
     */
    public  boolean deleteIntoSQL(){
        openConnection();
        boolean controllo=false;
        String sql="delete from sequenza where nome='" + this.getNome() + "'";

        if(updateQuery(sql)){
            controllo=true;
        }
        closeConnection();
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

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public String getNomeprogetto() {
        return nomeprogetto;
    }

    public void setNomeprogetto(String nomeprogetto) {
        this.nomeprogetto = nomeprogetto;
    }

    public double getPercentComplSeq() {
        return percentComplSeq;
    }

    public void setPercentComplSeq(double percentComplSeq) {
        this.percentComplSeq = percentComplSeq;
    }
}

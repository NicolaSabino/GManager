package Model;

import Model.Gruppi.Gruppo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe che rappresenta la struttura dati degli ordini
 */
public class Ordine extends Model {
    private int Id;
    private String Matricola;
    private float Prezzo;
    private String DataOrdine;
    private int Attivita;
    private String Approvazione;
    private String Descrizione;
    private int Quantita;
    private ArrayList<Map> votazione;


    /**
     * Costruttore vuoto
     */
    public Ordine(){
        return;
    }

    /**
     * Costruttore da sql
     *
     * @param id
     */
    public Ordine (int id){
        openConnection();
        String sql="select * from Ordine where id='"+ id + "'";
        ResultSet query= selectQuery(sql);
        try{
            if(query.next()){
                this.setId(query.getInt("id"));
                this.setMatricola(query.getString("matricola"));
                this.setDataOrdine(query.getString("dataordine"));
                this.setPrezzo(query.getFloat("prezzo"));
                this.setApprovazione(query.getString("approvazione"));
                this.setDescrizione(query.getString("descrizione"));
                this.setQuantita(query.getInt("quantita"));
                this.setAttivita(query.getInt("attività"));
            }

        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }

        this.popolaVotazione();

        return;
    }

    /**
     *Meotodo per acquisire dal db lo satto delle approvazioni
     */
    public void popolaVotazione(){
        openConnection();
        votazione=new ArrayList<>();
        String sql="select * from approvazioneordine where id_ordine='" + this.getId() + "'";
        ResultSet query= selectQuery(sql);
        try {
            while (query.next()){
                String matricola=query.getString("matricola");
                int id=query.getInt("id_ordine");
                String voto=query.getString("voto");

                Map map = new HashMap<>();
                map.put("matricola",matricola);
                map.put("id_ordine",id);
                map.put("voto",voto);

                votazione.add(votazione.size(),map);
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    /**
     *Meotodo per predisporre l'approvazione degli ordini sulla tabella "approvazioneordine" del db
     *
     * @param g
     */
    public void predisponiVotazioni(Gruppo g){
        openConnection();
        String sql;
        for (Utente appoggio:g.getElenco()){
            sql="insert into approvazioneordine values('" + appoggio.getMatricola() + "','"
                    + this.getId() + "','Da Approvare')";
            updateQuery(sql);
        }

        closeConnection();
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
        String sql="Insert into Ordine(matricola,prezzo,dataordine,attività,descrizione,quantita) values('"
                +   this.getMatricola()     + "','"
                +   this.getPrezzo()        + "',"
                +   " CURRENT_DATE ,'" +
                +   this.getAttivita() + "','"
                +   this.getDescrizione()   +"','"
                +   this.getQuantita()      + "')";

        if(insertQueryAutoIncrement(sql)){
            controllo=true;
        }
        setId(getId_auto_increment());
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

    public void aggiornaVotazione(String voto,String matricola){
        openConnection();
        String sql="update approvazioneordine set voto='" + voto + "' where id_ordine='" + this.getId() + "' and matricola='" + matricola + "'";
        updateQuery(sql);
        closeConnection();

        popolaVotazione();
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

    public float getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(float prezzo) {
        Prezzo = prezzo;
    }

    public String getDataOrdine() {
        return DataOrdine;
    }

    public void setDataOrdine(String dataOrdine) {
        DataOrdine = dataOrdine;
    }

    public int getAttivita() {
        return Attivita;
    }

    public void setAttivita(int attivita) {
        Attivita = attivita;
    }

    public String getApprovazione() {
        return Approvazione;
    }

    public void setApprovazione(String approvazione) {
        Approvazione = approvazione;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public int getQuantita() {
        return Quantita;
    }

    public void setQuantita(int quantita) {
        Quantita = quantita;
    }

    public ArrayList<Map> getVotazione() {
        return votazione;
    }
}

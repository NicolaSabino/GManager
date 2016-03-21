package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nicola on 08/02/16.
 */
public class Utente extends Model {
    //Attributi dell'utente
    private String Matricola;
    private String Pwd;
    private String Ruolo;
    private String Nome;
    private String Cognome;
    private String mail;
    private String telefono;
    private ArrayList<Attivita> incarichi;
    private ArrayList<Incontro> appuntamenti;

    /**
     * costruttore da SQL
     * @param chiave
     */

    public Utente (String chiave){
        openConnection();
        String sql ="select * from datilavorativi a join datianagrafici b " +
                "on a.matricola=b.matricola where a.matricola='"+ chiave +"'";
        ResultSet query= selectQuery(sql);
        try{
            if(query.next()) {
                this.setNome(query.getString("nome"));
                this.setCognome(query.getString("cognome"));
                this.setMail(query.getString("Mail"));
                this.setMatricola(query.getString("matricola"));
                this.setPwd(query.getString("pwd"));
                this.setRuolo(query.getString("Ruolo"));
                this.setTelefono(query.getString("telefono"));
            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            closeConnection();
        }

        this.popolaAppuntamenti();
        this.popolaIncarichi();

    }

    /**
     * Costruttore vuoto
     */
    public Utente(){
        return;
    }

    /**
     * permette mi modificare gli attributi di un
     * utente sia su datianagrafici che su datilavorativi
     *
     * @param var
     * @return
     */

    @Override
    public boolean updateIntoSQL(String... var) {
        boolean controllo = false;

        if(var[0]== "nome" || var[0]== "cognome" || var[0]== "telefono" || var[0]== "mail" ) {
            openConnection();
            String sql = "update datianagrafici set " + var[0] + " ='" + var[1]
                    + "' where matricola = '" + this.getMatricola() + "'";
            if(updateQuery(sql)) controllo=true;

        }else if(var[0]=="ruolo" || var[0]== "pwd"){
            openConnection();
            String sql = "update datilavorativi set " + var[0] + " ='" + var[1]
                    + "' where matricola = '" + this.getMatricola() + "'";

            if(updateQuery(sql)) controllo=true;

        }else{
            controllo=false;
        }
        return controllo;
    }
    

    /**
     * Override del metodo equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utente)) return false;

        Utente utente = (Utente) o;

        return getMatricola() != null ? getMatricola().equals(utente.getMatricola()) : utente.getMatricola() == null;

    }



    /**
     * Permette di aggiungere l'oggetto stesso nel db
     * @return
     */
    public boolean insertIntoSQL(){
        boolean controllo=false;
        openConnection();
        String sql="insert into datianagrafici values('" +
                this.getMatricola() + "','" +
                this.getNome()      + "','" +
                this.getCognome()   + "','" +
                this.getTelefono()  + "','" +
                this.getMail()      + "')";
        if(updateQuery(sql)){
            controllo=true;
        }

        if(controllo){
            String sql2="update datilavorativi set ruolo='" + this.getRuolo() +"' where matricola='" + getMatricola() +"'";
            if (updateQuery(sql2)){
                controllo=true;
            }
        }
        closeConnection();

        return controllo;

    }

    /**
     * Permette di eliminare l'oggetto dal db
     * @return
     */
    public boolean deleteIntoSQL(){
        openConnection();
        boolean controllo=false;
        String sql ="delete from datianagrafici where matricola='" + this.getMatricola() + "'";
        if(updateQuery(sql)){
            controllo=true;
        }
        closeConnection();
        return controllo;
    }


    public String selezionaSequenzaUtente(){
        openConnection();
        String ris="Unknown";
        String sql ="select a.nomesequenza from attività a join incarichi i on i.id=a.id where i.matricola='" + this.getMatricola() + "' limit 1";
        ResultSet query = selectQuery(sql);

        try {
            if(query.next()){
                ris=query.getString("nomesequenza");
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }

        return ris;

    }

    public void popolaIncarichi(){
        openConnection();
        this.incarichi=new ArrayList<Attivita>();
        String sql="select  a.id,a.nomesequenza,a.precedenza,a.descrizione,a.datainizio,a.datafineprevista,a.datafine,a.costo " +
                "from attività a join incarichi i on i.id=a.id where i.matricola='" + this.getMatricola() + "'";
        ResultSet query = selectQuery(sql);
        try{
            while(query.next()){
                Attivita appoggio= new Attivita();
                appoggio.setCosto(query.getDouble("costo"));
                appoggio.setDatafine(query.getString("datafine"));
                appoggio.setDatainizio(query.getString("datainizio"));
                appoggio.setDatafineprevista(query.getString("datafineprevista"));
                appoggio.setDescrizione(query.getString("descrizione"));
                appoggio.setId(query.getInt("id"));
                appoggio.setNomesequenza(query.getString("nomesequenza"));
                appoggio.setPrecedenza(query.getInt("precedenza"));

                incarichi.add(incarichi.size(),appoggio);
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
        return;
    }

    public void popolaAppuntamenti(){
        openConnection();
        this.appuntamenti=new ArrayList<Incontro>();
        String sql="select i.tipo,i.data,i.ora,i.luogo,i.verbale from incontro i join partecipazione p " +
                "on i.id=p.id " +
                "where p.matricola='" + this.getMatricola() + "'";
        ResultSet query= selectQuery(sql);
        try {
            while (query.next()){
                Incontro appoggio = new Incontro();
                appoggio.setTipo(query.getString("tipo"));
                appoggio.setData(query.getString("data"));
                appoggio.setOra(query.getString("ora"));
                appoggio.setLuogo(query.getString("luogo"));
                appoggio.setVerbale(query.getString("verbale"));

                appuntamenti.add(appuntamenti.size(),appoggio);
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
        return;
    }

    public boolean invita(Utente utente,Incontro incontro){
        openConnection();
        boolean controllo=false;
        String sql = "Insert into partecipazione(matricola,id,tipo,data,ora) values('" +
                utente.getMatricola()   + "','" +
                incontro.getId() +  "','" +
                incontro.getTipo() + "','" +
                incontro.getData() + "','" +
                incontro.getOra() + "')";
        if(updateQuery(sql)){
            controllo= true;
        }
        return controllo;
    }

    public void assegnaUtente(String matricola,int id){
        openConnection();
        String sql = "Insert into incarichi values('" + matricola + "','" + id +"')";
        updateQuery(sql);
        closeConnection();
    }

    public ArrayList selezionaIncarchidalDB(){
        openConnection();
        ArrayList elenco = new ArrayList();
        String sql="select * from incarichi i join datianagrafici l join attività a on i.matricola=l.matricola and a.id=i.id ";
        ResultSet query=selectQuery(sql);

        try {
            while(query.next()){
                String matricola        = query.getString("matricola");
                String nome             = query.getString("nome");
                String cognome          = query.getString("cognome");
                int id                  = query.getInt("id");
                String descrizione      = query.getString("descrizione");
                String datainizio         = query.getString("datainizio");
                String datafineprevista = query.getString("datafineprevista");

                Map map= new HashMap<>();
                map.put("matricola",matricola);
                map.put("nome",nome);
                map.put("cognome",cognome);
                map.put("id",id);
                map.put("descrizione",descrizione);
                map.put("datainizio",datainizio);
                map.put("datafineprevista",datafineprevista);
                elenco.add(elenco.size(),map);
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
        closeConnection();

        return elenco;
    }

    public void eliminaIncarico(String matricola,int id_attivita){
        openConnection();
        String sql="delete from incarichi where matricola='" + matricola + "' and id='" + id_attivita + "'";
        updateQuery(sql);
        closeConnection();
    }

    public void eliminaIncarico(String matricola){
        openConnection();
        String sql="delete from incarichi where matricola='" + matricola + "'";
        updateQuery(sql);
        closeConnection();
    }



    // getter and setter

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMatricola() {
        return Matricola;
    }

    public void setMatricola(String matricola) {
        Matricola = matricola;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    public String getRuolo() {
        return Ruolo;
    }

    public void setRuolo(String ruolo) {
        Ruolo = ruolo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Attivita> getIncarichi() {
        return incarichi;
    }

    public ArrayList<Incontro> getAppuntamenti() {
        return appuntamenti;
    }
}

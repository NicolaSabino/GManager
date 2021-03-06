package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe che rappresenta la struttura dati degli utenti sul db
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
     * Costruttore da SQL
     *
     * @param chiave matricola utente
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
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utente)) return false;

        Utente utente = (Utente) o;

        return getMatricola() != null ? getMatricola().equals(utente.getMatricola()) : utente.getMatricola() == null;

    }



    /**
     * Permette di inserire nel db l'occorrenza dell'utente
     *
     * @return booleano esito inserimento in db
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
     * Permette di eliminare gli utenti dal db
     *
     * @return booleano esito delete in db
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


    /**
     * Metodo che ritorna il nome delle sequenze di appartenenza dell' utente
     *
     * @return String del nome della sequenza
     */
    public String selezionaSequenzaUtente(){
        openConnection();
        String ris="Unknown";
        String sql ="select a.nomesequenza from attività a join incarichi i on i.id=a.id where i.matricola='"
                + this.getMatricola() + "' limit 1";
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

    /**
     * Metodo per acquisire all'occorrenza istanziata di utente tutti i relativi incarichi
     */
    public void popolaIncarichi(){
        openConnection();
        this.incarichi=new ArrayList<Attivita>();
        String sql="select  a.id,a.nomesequenza,a.precedenza,a.descrizione,a.datainizio," +
                "a.datafineprevista,a.datafine,a.costo " +
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

    /**
     * Metodo per acquisire all'occorrenza istanziata di utente tutti i relativi appuntamento
     */
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

    /**
     * Metodo per aggiungere una partecipazione di un utente ad un incontro
     *
     * @param utente occorrenza di utente da aggiungere ai partecipanti
     * @param incontro occorrenza dell'incontro
     * @return booleano di check del metodo
     */
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

    /**
     * Inserisce nel db nella tabella "incarichi" l'utente e l'attivita associata
     *
     * @param matricola matricola utente
     * @param id id Attività
     */
    public void assegnaUtente(String matricola,int id){
        openConnection();
        String sql = "Insert into incarichi values('" + matricola + "','" + id +"')";
        updateQuery(sql);
        closeConnection();
    }

    /**
     * select incarichi da db
     *
     * @return ArrayList elenco completo incarichi
     */
    public ArrayList selezionaIncarchidalDB(){
        openConnection();
        ArrayList elenco = new ArrayList();
        String sql="select * from incarichi i join datianagrafici l join attività a " +
                "on i.matricola=l.matricola and a.id=i.id ";
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

    /**
     *elimina incarichi di un utente in una specifica attività
     *
     * @param matricola matricola utente
     * @param id_attivita id attività
     */
    public void eliminaIncarico(String matricola,int id_attivita){
        openConnection();
        String sql="delete from incarichi where matricola='" + matricola + "' and id='" + id_attivita + "'";
        updateQuery(sql);
        closeConnection();
    }

    /**
     * eliminazione incarichi di un utente
     *
     * @param matricola matricola utente
     */
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

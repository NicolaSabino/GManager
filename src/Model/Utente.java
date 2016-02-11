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

        if(var[0]== "nome" || var[0]== "cognome" || var[0]== "telefono" || var[0]== "mail") {
            openConnection();
            String sql = "update datianagrafici set " + var[0] + " ='" + var[1]
                    + "'where matricola = '" + this.getMatricola() + "'";
            controllo=true;

        }else if(var[0]=="ruolo"){
            openConnection();
            String sql = "update datilavorativi set " + var[0] + " ='" + var[1]
                    + "'where matricola = '" + this.getMatricola() + "'";

            controllo=true;
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
            String sql2="update datilavorativi set ruolo='" + this.getRuolo() +"'";
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
        boolean controllo=false;
        String sql ="delete from datianagrafici where matricola='" + this.getMatricola() + "'";
        if(updateQuery(sql)){
            controllo=true;
        }
        return controllo;
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

    //metodo di controllo

    public List<Map> selectAllData() {
        ArrayList<Map> utenti = new ArrayList<>();

        openConnection();
        String sql = "select id, nomesequenza, costo, precedenza from attività";
        ResultSet query = selectQuery(sql);
        int i = 0;

        try {
            while (query.next()){
                Map user = new HashMap();

                user.put("id", query.getInt("id"));
                user.put("nomesequenza", query.getString("nomesequenza"));
                user.put("costo", query.getDouble("costo"));
                user.put("precedenza", query.getInt("precedenza"));

                utenti.add(i, user);
                i++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return utenti;
    }
}

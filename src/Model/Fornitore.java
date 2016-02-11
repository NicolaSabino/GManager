package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.*;

/**
 * Created by nicola on 10/02/16.
 */
public class Fornitore extends Model {
    private String Nome;
    private String Indirizzo;
    private String Telefono;
    private String Mail;
    private String CEO;


    /**
     * Costruttore vuoto
     */
    public Fornitore(){
        return;
    }

    /**
     * Costruttore da sql
     * @param chiave
     */
    public Fornitore(String chiave){
        openConnection();
        String sql= "select * from Fornitore where nome='"+ chiave +"'";
        ResultSet query= selectQuery(sql);
        try{
            if(query.next()) {
                this.setNome(query.getString("nome"));
                this.setIndirizzo(query.getString("indirizzo"));
                this.setTelefono(query.getString("telefono"));
                this.setMail(query.getString("mail"));
                this.setCEO(query.getString("ceo"));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
        return;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fornitore fornitore = (Fornitore) o;

        return Nome != null ? Nome.equals(fornitore.Nome) : fornitore.Nome == null;

    }


    @Override
    public boolean updateIntoSQL(String... var) {
        openConnection();
        boolean conrollo=false;
        String sql="update Fornitore set "
                + var[0] + "='" + var[1]
                +"' where nome='"+ this.getNome() + "'";
        if(updateQuery(sql)){
            conrollo=true;
        }
        closeConnection();

        return conrollo;
    }

    @Override
    public boolean insertIntoSQL() {
        openConnection();
        boolean controllo=false;

        String sql="Insert into Fornitore(nome,indirizzo,telefono,mail,ceo) values('"
                +   this.getNome()      + "','"
                +   this.getIndirizzo() + "','"
                +   this.getTelefono()  + "','"
                +   this.getMail()      + "','"
                +   this.getCEO()         +"')";

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

        String sql="delete from Fornitore where nome='" + this.getNome() + "'";

        if(updateQuery(sql)){
            controllo=true;
        }
        closeConnection();
        return controllo;
    }



    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getIndirizzo() {
        return Indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        Indirizzo = indirizzo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getCEO() {
        return CEO;
    }

    public void setCEO(String CEO) {
        this.CEO = CEO;
    }
}

package Model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by jacopo on 08/02/2016.
 */
public class Incontro extends Model {

    private String data;
    private String ora;
    private String luogo;
    private String tipo;
    private String verbale;
    private ArrayList<String> partecipazione;

    /**
     * Costruttore vuoto
     */
    public Incontro(){
        return;
    }



    /**
     * Costruttore da sql
     * @param chiave_data
     * @param chiave_tipo
     */
    public Incontro (String chiave_data, String chiave_tipo){
        openConnection();
        String sql="select data,ora,luogo,tipo,verbale from incontro where data='"+ chiave_data +"' and tipo='"+ chiave_tipo +"'";
        ResultSet query= selectQuery(sql);
        try {
            query.next();
            this.setData(query.getString("data"));
            this.setOra(query.getString("ora"));
            this.setLuogo(query.getString("luogo"));
            this.setTipo(query.getString("tipo"));
            this.setVerbale(query.getString("verbale"));
        }catch(SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
        this.partecipazione= generaPartecipazione();
        return;

    }


    @Override
    public boolean updateIntoSQL(String... var) {
        boolean controllo=false;
        openConnection();
        String sql="update incontro set " + var[0] + "='" + var[1]
                    + "' where data='" + this.getData()
                    + "' and tipo='"+ this.getTipo()
                    +"' and ora='" + this.getOra() + "'";


        if(updateQuery(sql)){
            controllo=true;
        }
        closeConnection();
        return controllo;
    }

    @Override
    public boolean insertIntoSQL() {
        boolean controllo=false;
        openConnection();
        String sql="insert into incontro values('"
                    + this.getTipo() + "','"
                    + this.getData() + "','"
                    + this.getOra()  + "','"
                    + this.getLuogo()+ "','"
                    + this.getVerbale() + "')";

        if(updateQuery(sql)){
            controllo=true;
        }
        closeConnection();
        return controllo;
    }

    @Override
    public boolean deleteIntoSQL (){
        boolean controllo= false;
        openConnection();
        String sql="delete from incontro where data='"+ this.getData()+ "' and tipo='"+ this.getTipo()+"'";
        if (updateQuery(sql)){
            controllo= true;
        }
        closeConnection();
        return controllo;

    }

    /**
     * Metodo che genera in automatico da sql l'elenco partecipazione
     * @return
     */
    protected ArrayList<String> generaPartecipazione(){
        ArrayList<String> appoggio= new ArrayList<String>();
        openConnection();
        String sql ="select matricola from partecipazione where tipo='"+ getTipo()+"' and data='"+ getData() + "' and ora='" + getOra() + "'";
        ResultSet query= selectQuery(sql);

        try{
            while (query.next()) {
                appoggio.add(appoggio.size(), query.getString("matricola"));
            }

        }catch(SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
        return appoggio;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Incontro incontro = (Incontro) o;

        if (data != null ? !data.equals(incontro.data) : incontro.data != null) return false;
        if (ora != null ? !ora.equals(incontro.ora) : incontro.ora != null) return false;
        if (luogo != null ? !luogo.equals(incontro.luogo) : incontro.luogo != null) return false;
        if (tipo != null ? !tipo.equals(incontro.tipo) : incontro.tipo != null) return false;
        return partecipazione != null ? partecipazione.equals(incontro.partecipazione) : incontro.partecipazione == null;

    }



    //getter and setter


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVerbale() {
        return verbale;
    }

    public void setVerbale(String verbale) {
        this.verbale = verbale;
    }

    public ArrayList<String> getPartecipazione() {
        return partecipazione;
    }

    public void setPartecipazione(ArrayList<String> partecipazione) {
        this.partecipazione = partecipazione;
    }
}


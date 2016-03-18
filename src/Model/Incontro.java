package Model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by jacopo on 08/02/2016.
 */
public class Incontro extends Model {
    private int    id;
    private String data;
    private String ora;
    private String luogo;
    private String tipo;
    private String verbale;

    /**
     * Costruttore vuoto
     */
    public Incontro(){
        return;
    }



    /**
     * Costruttore da sql
     * @param id
     */
    public Incontro (int id){
        openConnection();
        String sql="select data,ora,luogo,tipo,verbale from incontro where id='" + id + "'";
        ResultSet query= selectQuery(sql);
        try {
            if(query.next()) {
                this.setData(query.getString("data"));
                this.setOra(query.getString("ora"));
                this.setLuogo(query.getString("luogo"));
                this.setTipo(query.getString("tipo"));
                this.setVerbale(query.getString("verbale"));
                this.id=id;
            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
        return;

    }


    @Override
    public boolean updateIntoSQL(String... var) {
        boolean controllo=false;
        openConnection();
        String sql="update incontro set " + var[0] + "='" + var[1]
                + "' where id='" + this.id + "'";


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
        String sql="insert into incontro(tipo,data,ora,luogo,verbale) values('"
                    + this.getTipo() + "','"
                    + this.getData() + "','"
                    + this.getOra()  + "','"
                    + this.getLuogo()+ "','"
                    + this.getVerbale() + "')";

        if(insertQueryAutoIncrement(sql)){
            controllo=true;
        }
        closeConnection();
        id=getId_auto_increment();
        return controllo;
    }

    @Override
    public boolean deleteIntoSQL (){
        boolean controllo= false;
        boolean controllo2=false;
        openConnection();
        String sql="delete from incontro where id='" + this.getId() + "'";
        String sql2="delete from partecipazione where id='" + this.getId() + "'";

        if (updateQuery(sql)){
            controllo= true;
        }

        if(updateQuery(sql2)){
            controllo2=true;
        }
        closeConnection();
        return controllo && controllo2;

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


    public int getId() {
        return id;
    }
}


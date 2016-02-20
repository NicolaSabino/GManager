package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by nicola on 18/02/16.
 */
public class MessaggioBroadcast extends Model{
    private int id;
    private String data;
    private String messaggio;
    private String tipo;
    private String mittente;


    public MessaggioBroadcast() {
        return;
    }

    @Override
    public boolean updateIntoSQL(String... var) {
        return false;
    }

    @Override
    public boolean insertIntoSQL() {
        boolean controllo=false;
        openConnection();
        String sql="insert into broadcast(data,messaggio,tipo) values("
                +"CURRENT_DATE,'" + this.getMessaggio() + "','"
                + this.getTipo() + "')";
        if(updateIntoSQL()){
            controllo=true;
        }
        closeConnection();
        return controllo;
    }

    @Override
    public boolean deleteIntoSQL() {
        return false;
    }

    @Override
    public String toString() {
        String s;
        switch (this.tipo){
            case "TL":{
                s ="<html><b><font color=red>" + this.getData()+"  "+this.getMittente()+": </font></b>" + this.getMessaggio() + "</html>";
                break;
            }

            case "GL":{
                s ="<html><b><font color=blue>" + this.getData()+"  "+this.getMittente()+": </font></b>" + this.getMessaggio() + "</html>";
                break;
            }

            case "US":{
                s ="<html><b>" + this.getData()+"  "+this.getMittente()+": </b>" + this.getMessaggio() + "</html>";
                break;
            }

            case "AUTO":{
                s ="<html><b><font color=#778899>" + this.getData()+"  "+this.getMittente()+": </b>" + this.getMessaggio() + "</font></html>";
                break;
            }
            default: s ="<html><b>" + this.getData()+"  "+this.getMittente()+": </b>" + this.getMessaggio() + "</html>";
        }

        return s;
    }

    public ArrayList<String> selezionaNotifiche(){
        openConnection();
        ArrayList<String> ris = new ArrayList<String>();
        String sql = "select * from broadcast order by data";
        ResultSet query = selectQuery(sql);
        try {
            while(query.next()){
                MessaggioBroadcast appoggio = new MessaggioBroadcast();
                appoggio.setTipo(query.getString("tipo"));
                appoggio.setId(query.getInt("id"));
                appoggio.setData(query.getString("data"));
                appoggio.setMessaggio(query.getString("messaggio"));
                appoggio.setMittente(query.getString("mittente"));

                ris.add(ris.size(),appoggio.toString());
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
        return ris;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMittente() {
        return mittente;
    }

    public void setMittente(String mittente) {
        this.mittente = mittente;
    }
}

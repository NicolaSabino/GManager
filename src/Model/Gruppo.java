package Model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by nicola on 09/02/16.
 */
public class Gruppo extends Model{
    ArrayList<Utente> elenco;

    /**
     * costruttore vuoto di gruppo
     * @return
     */
    public Gruppo(){
        this.elenco=new ArrayList<Utente>();
        return;
    }


    /**
     * Genera un elenco di utenti che lavorano alla medesima Sequenza
     */
    public boolean createFrom(String... var){
        boolean controllo=false;
        openConnection();
        String sql;
        if(var[0]=="sequenza"){
            sql="select a.matricola,a.nome,a.cognome,a.telefono,a.mail from datianagrafici a join incarichi i join attività c " +
                    "on a.matricola=i.matricola and c.id=i.id where  c.nomesequenza='" + var[1] + "'";

        }else if(var[0]=="progetto"){
            sql="select a.matricola,a.nome,a.cognome,a.telefono,a.mail from datianagrafici a join incarichi i join attività c join sequenza s " +
                    "on a.matricola=i.matricola and c.id=i.id and  s.nome=c.nomesequenza where  s.nomeprogetto='" + var[1] +"'";

        }else if(var[0]=="utenti"){
            sql="select a.matricola,a.nome,a.cognome,a.telefono,a.mail from datianagrafici a";
        }else{
            sql="";
            controllo=false;
        }
        ResultSet query = selectQuery(sql);

        try {
            while (query.next()){
                Utente appoggio=new Utente();
                    appoggio.setMatricola(query.getString("matricola"));
                    appoggio.setNome(query.getString("nome"));
                    appoggio.setCognome(query.getString("cognome"));
                    appoggio.setTelefono(query.getString("telefono"));
                    appoggio.setMail(query.getString("mail"));

                elenco.add(elenco.size(),appoggio);
                controllo=true;
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }

        return controllo;
    }

    /*
     * non esiste una tabella rappresentante un insieme specifico di elementi nel db
     * pertanto i tre metodi astratti da implementare ritornano necessariamente false
     * senza compiere alcun operazione
     */

    @Override
    public boolean updateIntoSQL(String... var) {
        return false;
    }

    @Override
    public boolean insertIntoSQL() {
        return false;
    }

    @Override
    public boolean deleteIntoSQL() {
        return false;
    }

    //getter and setter

    public ArrayList<Utente> getElenco() {
        return elenco;
    }

    public void setElenco(ArrayList<Utente> elenco) {
        this.elenco = elenco;
    }
}

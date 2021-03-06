package Model.Gruppi;


import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Classe che permette di creare gruppi di utenti
 *
 */
public class Gruppo extends Model{
    ArrayList<Utente> elenco;

    /**
     * costruttore vuoto di gruppo
     *
     * @return
     */
    public Gruppo(){
        this.elenco=new ArrayList<Utente>();
        return;
    }


    /**
     * Genera un elenco di utenti che lavorano alla medesima Sequenza
     *
     * @param var la prima stringa è il nome degli attributi della tabella del db,
     *            le altre stringhe sono i valori da attribuire
     * @return booleano check del metodo
     */
    public boolean createFrom(String... var){
        boolean controllo=false;
        openConnection();
        String sql;
        if(var[0].equalsIgnoreCase("sequenza")){
            sql="select a.matricola,a.nome,a.cognome,a.telefono,a.mail,z.ruolo from datianagrafici a join incarichi i join attività c join datilavorativi z" +
                    " on a.matricola=i.matricola and c.id=i.id  and z.matricola=a.matricola where  c.nomesequenza='" + var[1] + "' group by z.matricola";

        }else if(var[0].equalsIgnoreCase("progetto")){
            sql="select a.matricola,a.nome,a.cognome,a.telefono,a.mail,z.ruolo from datianagrafici a join incarichi i join attività c join sequenza s join datilavorativi z" +
                    "on a.matricola=i.matricola and c.id=i.id and  s.nome=c.nomesequenza and a.matricola=z.matricola where  s.nomeprogetto='" + var[1] +"'";
        }else if(var[0].equalsIgnoreCase("utenti")){
            sql="select a.matricola,a.nome,a.cognome,a.telefono,a.mail,z.ruolo from datianagrafici a join datilavorativi z on a.matricola=z.matricola";
        }else if(var[0].equalsIgnoreCase("incontro")){
            sql="select a.matricola,a.nome,a.cognome,a.telefono,a.mail,z.ruolo from datianagrafici a join partecipazione p join datilavorativi z " +
                    "on a.matricola=p.matricola and z.matricola=a.matricola where p.id='" + var[1] + "'";
        }else if(var[0].equalsIgnoreCase("nome+cognome")){
            sql="select a.matricola,a.nome,a.cognome,a.telefono,a.mail,z.ruolo from datianagrafici a join datilavorativi z " +
                    "on a.matricola=z.matricola where a.nome like '" + var[1] + "%' and a.cognome like '" + var[2] + "%'";
        }else if(var[0].equalsIgnoreCase("direttivo")){
            sql="select a.matricola,a.nome,a.cognome,a.telefono,a.mail,z.ruolo from datianagrafici a join datilavorativi z " +
                    "on a.matricola=z.matricola where z.ruolo='GL' or z.ruolo='TL'";
        }else if(var[0].equalsIgnoreCase("non assegnati")){
            sql="select a.matricola,a.nome,a.cognome,a.telefono,a.mail from datianagrafici a  left join incarichi i " +
                    "on i.matricola=a.matricola where i.id is null";
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

                //da "non assegnati non ottengo il ruolo"
                if(!var[0].equalsIgnoreCase("non assegnati")) {
                    appoggio.setRuolo(query.getString("ruolo"));
                }

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
    public boolean deleteIntoSQL(){
        return false;
    }

    public void eliminaDaVotazione(int id){

            for(Utente appoggio:this.elenco){
                openConnection();
                String sql = "delete from approvazioneordine where id_ordine='" + id + "' and matricola='" + appoggio.getMatricola() + "'";
                updateQuery(sql);
                closeConnection();
            }

    }

    //getter and setter

    public ArrayList<Utente> getElenco() {
        return elenco;
    }

    public void setElenco(ArrayList<Utente> elenco) {
        this.elenco = elenco;
    }
}

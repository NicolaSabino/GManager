package Model;

import com.sun.javafx.sg.prism.NGShape;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicola on 08/02/16.
 */
public class Progetto extends Model {

    private ArrayList<Sequenza> stato;
    private String Nome;
    private String Deadline;
    private double Costo;
    private double perccompletamentoProgetto;

    /**
     * Costruttore vuoto
     */
    public Progetto(){
        return;
    }

    /**
     * Costruttore da sql
     * @param chiave
     */
    public Progetto(String chiave){
        openConnection();
        this.setNome(chiave);
        //prelevo dal db i nomi delle sequenze collegate al progetto
        String sql= "select nome from sequenza where nomeprogetto='"+ chiave + "'";
        ResultSet query= selectQuery(sql);

        //creo una lista contenente l'elenco delle sequenze da istanziare in stato
        List<String> seq = new ArrayList<String>();

        try {
            while(query.next()){
                seq.add(seq.size(),query.getString("nome"));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }


        //creo un array per contenere lo stato di progetto
        this.stato=new ArrayList<Sequenza>();

        //per ogni elemento dell'array 'seq' creo una sequenza da inserire nello 'stato' di progetto
        for (String appoggio:seq){
            Sequenza s= new Sequenza(appoggio);

            stato.add(stato.size(),s);

        }

        String sql2="select deadline from progetto where nome='" + chiave + "'";
        ResultSet query2 = selectQuery(sql2);

        try {
            if(query2.next()){
                this.setDeadline(query.getString("deadline"));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }

        this.setCosto(this.calcola_costo());
        return;
    }

    /**
     * Calcola il costo di un progetto
     * @return
     */
    protected double calcola_costo(){
        double sum=0;
        for (Sequenza appoggio:this.stato){
            sum += appoggio.getCosto();
        }
        return sum;
    }


    @Override
    public boolean updateIntoSQL(String... var) {
        openConnection();
        boolean conrollo=false;
        String sql="update progetto set "
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
        boolean controllo=false;
        openConnection();

        String sql="insert into progetto values('"
                + this.getNome() + "','"
                + this.getDeadline() + "')";

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
        String sql="delete from progetto where sequenza='" + this.getNome() + "'";

        if(updateQuery(sql)){
            controllo=true;
        }
        closeConnection();
        return controllo;
    }


    //getter and setter

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double costo) {
        Costo = costo;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public ArrayList<Sequenza> getStato() {
        return stato;
    }

    public void setStato(ArrayList<Sequenza> stato) {
        this.stato = stato;
    }
}

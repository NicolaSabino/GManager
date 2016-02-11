package Model;

import com.sun.javafx.sg.prism.NGShape;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by nicola on 08/02/16.
 */
public class Progetto extends Model {

    private ArrayList<Sequenza> stato;
    private String Nome;
    private String Deadline;
    private double Costo;

    public Progetto(String chiave){
        openConnection();
        String sql= "select * from sequenza where nomeprogetto= '"+ chiave + "'";
        ResultSet query= selectQuery(sql);

        try{
          Sequenza appoggio= new Sequenza();
            while (query.next()){
                appoggio.setCosto(query.getDouble("costo"));
                appoggio.setNome(query.getString("nome"));
                appoggio.setFine(query.getDate("fine"));
                appoggio.setPercentComplSeq(query.getFloat("percentcomplseq"));
                stato.add(stato.size(), appoggio);
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }finally {
            openConnection();
        }

        this.setCosto(calcola_costo());
        return;
    }

    protected double calcola_costo(){
        double sum=0;
        for (Sequenza appoggio:this.stato){
            sum += appoggio.getCosto();
        }
        return sum;
    }

    public boolean cambio_nome(String old_nome, String  new_nome){
        boolean controllo= false;
        if (old_nome.equals(getNome())){
            this.setNome(new_nome);
            openConnection();
            String sql= "update from progetto nome='"+ new_nome +"' where nome='" + old_nome +"'";
            if (updateQuery(sql)){
                controllo=true;
            }
            closeConnection();
        }
        return controllo;
    }

    public boolean cambio_Deadline(String old_dead, String new_dead){
        boolean controllo= false;
        if (old_dead.equals(getDeadline())){
            setDeadline(new_dead);
            openConnection();
            String sql= "update from progetto deadline='"+ new_dead +"' where nome='"+ this.getNome()+ "'";
            if (updateQuery(sql)){
                controllo=true;
            }
            closeConnection();
        }
        return controllo;
    }

    public boolean deletefromsql (){
        boolean controllo= false;
        openConnection();
        String sql= "delete from progetto where nome='"+ this.getNome()+ "'";
        if (updateQuery(sql)){
            controllo= true;
        }
        closeConnection();
        return controllo;
    }

    public ArrayList<String> generastato (){
        ArrayList appoggio= new ArrayList();
        openConnection();
        String sql= "select nome, costo, fine, nomeprogetto from sequenza where nomeprogetto='"+ getNome()+ "'";
        ResultSet query= selectQuery(sql);
        try{
            while (query.next()){
                appoggio.add(appoggio.size(), query.getString("nome"));
                appoggio.add(appoggio.size(), query.getString("nomeprogetto"));
                appoggio.add(appoggio.size(), query.getDouble("costo"));
                appoggio.add(appoggio.size(), query.getString("fine"));

            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }
        return appoggio;
    }



    public Progetto(){
        return;
    }

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

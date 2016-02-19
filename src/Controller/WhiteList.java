package Controller;
import Model.Gruppo;
import Model.Utente;
import sun.plugin.javascript.navig.Array;
import sun.security.util.Password;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by nicola on 14/02/16.
 */



public class WhiteList {
    // ACCESS-CONTROL-LIST
    //gli oggetti istanziati da questa classe hanno il compito di controllare l'accesso alle risorse da parte dei vari utenti



    private String matricola;
    private char[] passwd;
    private Permesso permesso;



    /**
     * Costruttore di Acl
     * @param matricola
     * @param passwd
     */
    public WhiteList(String matricola, char[] passwd){

        this.setMatricola(matricola);
        this.setPasswd(passwd);
        this.controllo();
        return;
    }

    /**
     * Metodo che controlla l'effettviva presenza dell'utente all'interno del db
     * e ne scandisce i permermessi
     * @return permessi di accesso al programma
     */
    protected  void controllo(){


            //  creo un utente a partire dalla matricola inserita nella view
            //  se la matricola non è presente ne db creo un utente senza alcun valore
            Utente u = new Utente(this.getMatricola());
        if(u.getMatricola()==null) {
            this.setPermesso(Permesso.NR);
        }else{

            char[] pwd_conv = u.getPwd().toCharArray(); // password prelevata dal db e adeguatamente converitita

            if (this.getPasswd().length != pwd_conv.length) {
                this.setPermesso(Permesso.NR);
            } else if(Arrays.equals (this.getPasswd(),pwd_conv)){
                switch(u.getRuolo()) {
                    case "US":{
                        this.setPermesso(Permesso.US);
                        break;
                    }
                    case "GL":{
                        this.setPermesso(Permesso.GL);
                        break;
                    }

                    case "TL":{
                    this.setPermesso(Permesso.TL);
                    }

                    default: this.setPermesso(Permesso.NR);
            }
        }
        }

        return;
    }

    public Permesso getPermesso() {
        return permesso;
    }

    protected void setPermesso(Permesso permesso) {
        this.permesso = permesso;
    }

    protected String getMatricola() {
        return matricola;
    }

    protected void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    protected char[] getPasswd() {
        return passwd;
    }

    protected void setPasswd(char[] passwd) {
        this.passwd = passwd;
    }
}

package Controller;
import Model.Gruppo;
import Model.Utente;

/**
 * Created by nicola on 14/02/16.
 */



public class Acl {
    // ACCESS-CONTROL-LIST
    //gli oggetti istanziati da questa classe hanno il compito di controllare l'accesso alle risorse da parte dei vari utenti



    private String matricola;
    private String passwd;
    private Permesso permesso;



    /**
     * Costruttore di Acl
     * @param matricola
     * @param passwd
     */
    public Acl(String matricola,String passwd){
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
        Gruppo elenco_utenti = new Gruppo();
        this.setPermesso(Permesso.NR);

        if((this.getMatricola()=="Admin") && (this.getPasswd()=="admin")){
            this.setPermesso(Permesso.Admin);
        }else {

            //genero l'elenco degli utenti regolarmente inseriti nel programma
            elenco_utenti.createFrom("utenti");

            //scorro tutti gli utenti all'interno del gruppo
            for (Utente appoggio : elenco_utenti.getElenco()) {

                if ((appoggio.getMatricola() == this.getMatricola())) { //match della matricola

                    String ruolo = appoggio.getRuolo();

                    if (ruolo == "TL" && (appoggio.getPwd()==this.getPasswd())) {
                        this.setPermesso(Permesso.US);
                    } else if (ruolo == "GL" && (appoggio.getPwd()==this.getPasswd())) {
                        this.setPermesso(Permesso.GL);
                    } else if (appoggio.getPwd()==this.getPasswd()) {
                        this.setPermesso(Permesso.US);
                    }
                }
            }
        }

        return ;
    }

    public Permesso getPermesso() {
        return permesso;
    }

    public void setPermesso(Permesso permesso) {
        this.permesso = permesso;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}

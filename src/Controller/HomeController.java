package Controller;

import Model.Sequenza;
import Model.Utente;
import View.Home;
import View.RootFrame;
import View.Spalla;
import sun.security.util.Password;

/**
 * Created by nicola on 16/02/16.
 */
public class HomeController {
    RootFrame rootFrame;
    Utente utilizzatore;
    Home    home;

    public HomeController(Permesso p, Spalla s,RootFrame r, String mat) {
        this.utilizzatore= new Utente(mat);
        this.rootFrame=r;
        this.home=new Home();

        switch (p){
            case US:{
                //setto le informazioni dell'utente in alto a sinistra sulla spalla
                s.usMode(utilizzatore);

                r.setMainScrollPane(home.getPannelloPrincipale());
                Sequenza sequenzaDiAppartenenza = new Sequenza(utilizzatore.selezionaSequenzaUtente());
                home.setSequenza(utilizzatore.selezionaSequenzaUtente());
                home.setProgressBarCompl((int) sequenzaDiAppartenenza.getPercentComplSeq());
                break;
            }
            case GL:{
                s.proMode(utilizzatore);
                r.setMainScrollPane(home.getPannelloPrincipale());
                Sequenza sequenzaDiAppartenenza = new Sequenza(utilizzatore.selezionaSequenzaUtente());
                home.setSequenza(utilizzatore.selezionaSequenzaUtente());
                home.setProgressBarCompl((int) sequenzaDiAppartenenza.getPercentComplSeq());
                break;
            }
            case TL:{
                s.proMode(utilizzatore);
                r.setMainScrollPane(home.getPannelloPrincipale());
                Sequenza sequenzaDiAppartenenza = new Sequenza(utilizzatore.selezionaSequenzaUtente());
                home.setSequenza(utilizzatore.selezionaSequenzaUtente());
                home.setProgressBarCompl((int) sequenzaDiAppartenenza.getPercentComplSeq());
                break;
            }

        }
    }
}

package Controller;

import Model.Attivita;
import Model.MessaggioBroadcast;
import Model.Sequenza;
import Model.Utente;
import View.*;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by nicola on 16/02/16.
 */
public class HomeController {
    RootFrame rootFrame;
    Utente utilizzatore;

    Home home;

    public HomeController(Permesso p, Spalla s,RootFrame r, String mat) {
        this.utilizzatore= new Utente(mat);
        this.rootFrame=r;
        this.home=new Home();

        switch (p){
            case US:{
                //setto le informazioni dell'utente in alto a sinistra sulla spalla
                s.usMode(utilizzatore);
                break;
            }
            case GL:{
                s.proMode(utilizzatore);
                break;
            }
            case TL:{
                s.proMode(utilizzatore);
                break;
            }

        }

        r.setMainScrollPane(home.getPannelloPrincipale());
        Sequenza sequenzaDiAppartenenza = new Sequenza(utilizzatore.selezionaSequenzaUtente());
        home.setSequenza(utilizzatore.selezionaSequenzaUtente());
        home.setProgressBarCompl((int) sequenzaDiAppartenenza.getPercentComplSeq());

        //genero gli incarichi dell'utilizzatore e gli appuntamenti
        utilizzatore.popolaIncarichi();
        utilizzatore.popolaAppuntamenti();

        //mostro a video gli incarichi
        TabellaAttivita t= new TabellaAttivita();
        t.setModelTabella(utilizzatore.getIncarichi());
        home.setScrollCompiti(t.getPannelloPrincipale());

        //mostro a video gli appuntamenti
        TabellaIncontri t2= new TabellaIncontri();
        t2.setModelTabella(utilizzatore.getAppuntamenti());
        home.setScrollEventi(t2.getPannelloPrincipale());

        //mostro l'elenco delle notifiche
        MessaggioBroadcast m = new MessaggioBroadcast();
        ArrayList<String> a = m.selezionaNotifiche();
        ListaNotifiche n = new ListaNotifiche();
        n.setNotifiche(a);
        home.setScrollNotifiche(n.getPannelloPrincipale());


    }
}

package Controller;

import Model.Attivita;
import Model.MessaggioBroadcast;
import Model.Sequenza;
import Model.Utente;
import View.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by nicola on 16/02/16.
 */
public class HomeController {
    RootFrame rootFrame;
    Utente utilizzatore;
    ListaNotifiche notifiche;
    Home home;

    /**
     * Costruttore di home controller
     * @param p
     * @param s spalla dell'interfaccia grafica
     * @param r frame principale dove settare le viste
     * @param mat matricola dell'utilizzatore
     */
    public HomeController(Permesso p,RootFrame r, String mat) {
        this.utilizzatore= new Utente(mat);
        this.rootFrame=r;
        this.home=new Home();

        r.setMainScrollPane(home.getPannelloPrincipale());
        Sequenza sequenzaDiAppartenenza = new Sequenza(utilizzatore.selezionaSequenzaUtente());
        home.setSequenza(utilizzatore.selezionaSequenzaUtente());
        home.setProgressBarCompl((int) sequenzaDiAppartenenza.getPercentComplSeq());


        //mostro a video gli incarichi
        TabellaAttivita t= new TabellaAttivita();
        t.setModelTabella(utilizzatore.getIncarichi());
        home.setScrollCompiti(t.getPannelloPrincipale());

        //mostro a video gli appuntamenti
        TabellaIncontri t2= new TabellaIncontri();
        t2.setModelTabella(utilizzatore.getAppuntamenti());
        home.setScrollEventi(t2.getPannelloPrincipale());

        //mostro l'elenco dei messaggi-notifiche
        MessaggiController m = new MessaggiController(this.utilizzatore,this.home);
        this.notifiche=m.getListaNotifiche();
        home.setScrollNotifiche(notifiche.getPannelloPrincipale());

    }

    public void apriHome(){
        rootFrame.setMainScrollPane(home.getPannelloPrincipale());
    }

}

package Controller;

import Model.MessaggioBroadcast;
import Model.Progetto;
import Model.Utente;
import View.Gestisci;
import View.RootFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nicola on 02/03/16.
 */
public class GestisciController {
    private RootFrame rootFrame;
    private Gestisci gestisci;
    private Utente utilizzatore;

    public GestisciController(RootFrame rootFrame, Utente utente) {
        this.rootFrame=rootFrame;
        this.gestisci= new Gestisci();
        this.utilizzatore=utente;

        //listner
        listnerCrea();
        return;
    }

    public void apriGestisci(){
        rootFrame.setMainScrollPane(gestisci.getPanelloPrincipale());
    }

    protected void listnerCrea(){
        JButton crea = gestisci.getButtonCreaProgetto();
        crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaProgetto();
            }
        });
    }

    protected void creaProgetto(){
        Progetto p = gestisci.getParametriCreaProgetto();
        p.insertIntoSQL();
        //inserisco un nuovo messaggio
        MessaggioBroadcast messaggioBroadcast = new MessaggioBroadcast();
        messaggioBroadcast.setMessaggio(utilizzatore.getNome() + " " + utilizzatore.getCognome() +
                 " ha creato un nuovo progetto:" + p.getNome());

        //aggiorno l'elenco dei progetti
        gestisci.popolaProgetti();
    }
}

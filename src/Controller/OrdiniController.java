package Controller;


import Model.Attivita;
import Model.Gruppi.Gruppo;
import Model.Gruppi.GruppoOrdini;
import Model.MessaggioBroadcast;
import Model.Utente;
import Model.Ordine;
import View.Gestisci;
import View.Ordini;
import View.RootFrame;
import View.TabellaOrdini;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by edoardo on 06/03/16.
 */
public class OrdiniController {
    private RootFrame rootFrame;
    private Ordini ordini;
    private Utente utilizzatore;
    private Gestisci gestisci;
    private HomeController homeController;

    public OrdiniController(RootFrame rootFrame, Utente utilizzatore,HomeController homeController) {

        this.rootFrame = rootFrame;
        this.ordini = new Ordini();
        this.utilizzatore = utilizzatore;
        this.homeController=homeController;

        //genero la lista degli ordini da visualizzare
        GruppoOrdini o = new GruppoOrdini();
        TabellaOrdini t = new TabellaOrdini(utilizzatore.getMatricola());
        t.setModelTabella(o.getStato());
        ordini.setScrollOrdini(t.getPannelloPrincipale());

        listnerNuovoOrdine();
        return;

    }


    public void listnerNuovoOrdine(){
        JButton nuovo = ordini.getAggiungiOrdineButton();
        nuovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaNuovoOrdine();
            }
        });
    }

    protected void creaNuovoOrdine(){
        Ordine o = ordini.getModifiche(utilizzatore);
        if(!o.insertIntoSQL()){
            JOptionPane.showMessageDialog(new JFrame("errore"),"Errore nell'inserimento dell'ordine");
        }

        //predispongo le votazioni
        Gruppo g =new Gruppo();
        g.createFrom("direttivo");
        o.predisponiVotazioni(g);



        //aggiorno la tabella
        GruppoOrdini a = new GruppoOrdini();
        TabellaOrdini t = new TabellaOrdini(utilizzatore.getMatricola());
        t.setModelTabella(a.getStato());
        ordini.setScrollOrdini(t.getPannelloPrincipale());

        //inserisco un messaggio
        MessaggioBroadcast messaggio = new MessaggioBroadcast();
        messaggio.setTipo("AUTO");
        messaggio.setMittente("AUTO");
        messaggio.setMessaggio(utilizzatore.getNome() + " " + utilizzatore.getCognome() +
                " ha creato un nuovo ordine:" + o.getDescrizione());
        messaggio.insertIntoSQL();
        //aggiorno la home
        this.homeController.getMessaggiController().aggiorna();
        //aggiorno gestisci
        gestisci.popolaOrdini();

        //ripulisco ordini
        ordini.clean();
    }
    public void apriOrdini(){
        rootFrame.setMainScrollPane(ordini.getPannelloPrincipale());
    }

    public void setGestisci(Gestisci gestisci){
        this.gestisci=gestisci;
    }
}

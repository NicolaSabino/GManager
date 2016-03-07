package Controller;


import Model.Gruppi.GruppoOrdini;
import Model.Utente;
import Model.Ordine;
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

    public OrdiniController(RootFrame rootFrame, Utente utilizzatore) {

        this.rootFrame = rootFrame;
        this.ordini = new Ordini();
        this.utilizzatore = utilizzatore;

        //genero la lista degli ordini da visualizzare
        GruppoOrdini o = new GruppoOrdini();
        TabellaOrdini t = new TabellaOrdini();
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

        //aggiorno la tabella
        GruppoOrdini a = new GruppoOrdini();
        TabellaOrdini t = new TabellaOrdini();
        t.setModelTabella(a.getStato());
        ordini.setScrollOrdini(t.getPannelloPrincipale());
    }
    public void apriOrdini(){
        rootFrame.setMainScrollPane(ordini.getPannelloPrincipale());
    }
}

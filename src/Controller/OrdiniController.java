package Controller;


import Model.Gruppi.GruppoOrdini;
import Model.Model;
import Model.Utente;
import View.Ordini;
import View.RootFrame;
import View.TabellaOrdini;

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

        return;

    }

    public void apriOrdini(){
        rootFrame.setMainScrollPane(ordini.getPannelloPrincipale());
    }
}

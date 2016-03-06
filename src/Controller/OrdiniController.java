package Controller;

import Model.Utente;
import View.Ordini;
import View.RootFrame;

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

        return;

    }

    public void apriOrdini(){
        rootFrame.setMainScrollPane(ordini.getPannelloPrincipale());
    }
}

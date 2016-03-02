package Controller;

import Model.Utente;
import View.Impostazioni;
import View.RootFrame;

/**
 * Created by nicola on 02/03/16.
 */
public class ImpostazioniController {
    private RootFrame rootFrame;
    private Impostazioni impostazioni;

    public ImpostazioniController(RootFrame rootFrame, Utente utilizzatore) {
        this.rootFrame=rootFrame;
        this.impostazioni= new Impostazioni();
        return;
    }

    public void apriImpostazioni(){
        rootFrame.setMainScrollPane(impostazioni.getPannelloPrincipale());
    }
}

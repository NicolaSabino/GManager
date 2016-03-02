package Controller;

import View.Impostazioni;
import View.Ricerca;
import View.RootFrame;

/**
 * Created by nicola on 02/03/16.
 */
public class RicercaController {
    RootFrame rootFrame;
    public RicercaController(RootFrame rootFrame) {
        this.rootFrame=rootFrame;
        return;
    }

    public void apriPannelloRicerca(){
        rootFrame.setMainScrollPane(new Ricerca().getPrimoPanel());
    }
}

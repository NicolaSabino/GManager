package Controller;

import View.Gestisci;
import View.RootFrame;

/**
 * Created by nicola on 02/03/16.
 */
public class GestisciController {
    private RootFrame rootFrame;
    private Gestisci gestisci;

    public GestisciController(RootFrame rootFrame) {
        this.rootFrame=rootFrame;
        this.gestisci= new Gestisci();
        return;
    }

    public void apriGestisci(){
        rootFrame.setMainScrollPane(gestisci.getPanelloPrincipale());
    }
}

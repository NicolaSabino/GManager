package Controller;

import Model.Utente;
import View.RootFrame;
import View.Spalla;

/**
 * Created by nicola on 02/03/16.
 */
public class SpallaController {

    private Utente utilizzatore;
    private Permesso permesso;
    private RootFrame rootFrame;

    public SpallaController(Permesso p, Spalla s, String mat,RootFrame r) {

        this.permesso=p;
        this.utilizzatore= new Utente(mat);
        this.rootFrame=r;

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
        return;

        //faccio partire i controller delle varie schede
        HomeController h = new HomeController(permesso,view,loginView.getLog_mat());
        HomeController h = new HomeController()
    }
}

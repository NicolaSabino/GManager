package Controller;

import Model.Utente;
import View.Home;
import View.RootFrame;
import View.Spalla;
import sun.security.util.Password;

/**
 * Created by nicola on 16/02/16.
 */
public class HomeController {
    RootFrame rootFrame;
    Utente utilizzatore;
    Home    home;

    public HomeController(Permesso p, Spalla s,RootFrame r, String mat) {
        this.utilizzatore= new Utente(mat);
        this.rootFrame=r;
        this.home=new Home();

        switch (p){
            case US:{
                s.usMode(utilizzatore);

                r.setMainScrollPane(home.getPannelloPrincipale());
                home.setSequenza("Motore");
            }
            case GL:{
                s.proMode(utilizzatore);

            }
            case TL:{
                s.proMode(utilizzatore);
            }

        }
    }
}

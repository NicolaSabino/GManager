package Controller;

import Model.Utente;
import View.Spalla;
import sun.security.util.Password;

/**
 * Created by nicola on 16/02/16.
 */
public class HomeController {
    Utente utilizzatore;

    public HomeController(Permesso p, Spalla s, String mat) {
        this.utilizzatore= new Utente(mat);
        switch (p){
            case US:{
                s.usMode(utilizzatore);
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

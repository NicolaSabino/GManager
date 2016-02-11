import Model.*;

import java.util.List;
import java.util.Map;

/**
 * Created by nicola on 10/02/16.
 */
public class GManager {
    public static void main(String args[]) {
        Progetto p2 = new Progetto("P2");


        for(Sequenza appoggio:p2.getStato()){
            for(Attivita app:appoggio.getStato()){
                System.out.println(app.getId()+" "+app.getCosto()+" "+app.getDescrizione());
            }
        }

        System.out.println(p2.getDeadline());
    }

}

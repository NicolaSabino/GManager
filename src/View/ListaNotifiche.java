package View;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by nicola on 18/02/16.
 */
public class ListaNotifiche {
    private JPanel pannelloPrincipale;
    private JList notifiche;

    public void setNotifiche(ArrayList<String> a) {
        this.notifiche = new JList(a.toArray());
    }

    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }
}

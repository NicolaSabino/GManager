package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by nicola on 18/02/16.
 */
public class ListaNotifiche {
    private JPanel pannelloPrincipale;

    private JLabel lableBroadcast;
    private JScrollPane scrollNotifiche;
    private JButton ButtonNuovo;

    public ListaNotifiche(ArrayList<String> a) {
        JList notifiche = new JList(a.toArray());
        notifiche.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        notifiche.setVisibleRowCount(-1);
        notifiche.setVisible(true);
        scrollNotifiche.setViewportView(notifiche);

    }

    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }

    public JButton getButtonNuovo() {
        return ButtonNuovo;
    }

    public void aggiorna(){
        this.pannelloPrincipale.repaint();
    }

}



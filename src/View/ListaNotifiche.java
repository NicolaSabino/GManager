package View;

import javax.swing.*;
import java.util.ArrayList;


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

    /**
     * Metodo che aggiorna la view
     */
    public void aggiorna(){
        this.pannelloPrincipale.repaint();
    }

    //getter e setter
    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }

    public JButton getButtonNuovo() {
        return ButtonNuovo;
    }



}



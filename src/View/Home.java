package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Scermata delle impostazioni
 */
public class Home extends JPanel {
    private JPanel pannelloPrincipale;
    private JScrollPane scrollEventi;
    private JScrollPane scrollCompiti;
    private JLabel nomeSequenza;
    private JProgressBar progressBarCompl;
    private JScrollPane scrollNotifiche;


    public Home() {
        setVisible(true);
        progressBarCompl.setVisible(true);

    }

    //getter e setter
    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }

    public void setSequenza(String nome) {
        nomeSequenza.setText(nome);
        return;
    }

    public void setProgressBarCompl(int n) {
        progressBarCompl.setString(n + "%");
        progressBarCompl.setValue(n);
        progressBarCompl.setStringPainted(true);
        return;
    }

    public void setScrollCompiti(Component panel) {
        this.scrollCompiti.setViewportView(panel);
    }

    public void setScrollEventi(Component panel) {
        this.scrollEventi.setViewportView(panel);
    }

    public void setScrollNotifiche(Component panel) {
        this.scrollNotifiche.setViewportView(panel);
    }

    public JScrollPane getScrollNotifiche(){
        return scrollNotifiche;
    }
}


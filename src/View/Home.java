package View;

import javax.swing.*;

/**
 * Created by edoardo on 15/02/16.
 */
public class Home extends JFrame {
    private JPanel pannelloPrincipale;
    private JScrollPane scrollEventi;
    private JScrollPane scrollCompiti;
    private JLabel nomeSequenza;
    private JProgressBar progressBarCompl;

    public Home() {
        pack();
        setContentPane(pannelloPrincipale);
        setVisible(true);

    }

    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }

    public void setSequenza(String nome){
        nomeSequenza.setText(nome);
        return;
    }
}

package View;

import javax.swing.*;

/**
 * Created by edoardo on 15/02/16.
 */
public class Home extends JFrame {
    private JPanel panelHome;
    private JScrollPane scrollEventi;
    private JScrollPane scrollCompiti;
    private JLabel nomeSequenza;
    private JProgressBar progressBarCompl;

    public Home() {
        pack();
        setContentPane(panelHome);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }
}

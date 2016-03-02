package View;

import javax.swing.*;

/**
 * Created by edoardo on 01/03/16.
 */
public class Ricerca extends JFrame {
    private JPanel primoPanel;
    private JPanel panelRicerca;
    private JComboBox comboBoxRicerca;
    private JTextField primoCampoRicerca;
    private JTextField secondoCampoRicerca;


    public Ricerca(){
        setContentPane(primoPanel);
        pack();
        setSize(1900, 900);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

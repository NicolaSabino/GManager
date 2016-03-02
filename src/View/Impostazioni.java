package View;

import javax.swing.*;

/**
 * Created by edoardo on 02/03/16.
 */
public class Impostazioni extends JFrame{


    private JPanel pannellone;
    private JTextField campoNome;
    private JTextField campoPassword;
    private JTextField campoTelefono;
    private JTextField campoCognome;
    private JTextField campoEmail;
    private JButton buttonEdit;
    private JButton buttonSave;
    private JButton buttonLogout;

    public Impostazioni(){
        pack();
        setContentPane(pannellone);
        setSize(1900, 900);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
